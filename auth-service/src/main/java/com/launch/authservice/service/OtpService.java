package com.launch.authservice.service;

import com.launch.authservice.entity.EmailOtp;
import com.launch.authservice.repository.EmailOtpRepository;
import com.launch.authservice.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Locale;
import java.util.UUID;

@Service
public class OtpService {

    private static final Logger log =
            LoggerFactory.getLogger(OtpService.class);

    private static final int MAX_ATTEMPTS = 5;
    private static final Duration OTP_VALIDITY = Duration.ofMinutes(5);

    private final EmailOtpRepository otps;
    private final UserRepository users;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final SecureRandom random = new SecureRandom();

    public OtpService(
            EmailOtpRepository otps,
            UserRepository users,
            EmailService emailService,
            PasswordEncoder passwordEncoder
    ) {
        this.otps = otps;
        this.users = users;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    private String normalize(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Email is required");
        }

        return value.trim().toLowerCase(Locale.ROOT);
    }

    @Transactional
    public void request(String rawEmail) {

        String normalizedEmail = normalize(rawEmail);

        log.info(
                "OTP request received for email={}",
                normalizedEmail
        );

        if (!normalizedEmail.endsWith("@gmail.com")) {
            log.warn(
                    "OTP rejected because email is not Gmail: {}",
                    normalizedEmail
            );

            throw new IllegalArgumentException(
                    "Only Gmail addresses are supported"
            );
        }

        if (users.existsByEmailIgnoreCase(normalizedEmail)) {

            log.warn(
                    "OTP rejected because email already exists: {}",
                    normalizedEmail
            );

            throw new IllegalStateException(
                    "Email is already registered"
            );
        }

        otps.deleteByEmail(normalizedEmail);

        String otp = String.format(
                "%06d",
                random.nextInt(1_000_000)
        );

        EmailOtp record = new EmailOtp();

        record.setEmail(normalizedEmail);
        record.setOtpHash(passwordEncoder.encode(otp));
        record.setVerificationTokenHash(null);
        record.setExpiresAt(
                Instant.now().plus(OTP_VALIDITY)
        );
        record.setAttempts(0);
        record.setVerified(false);
        record.setCreatedAt(Instant.now());

        otps.save(record);

        log.info(
                "OTP generated and stored successfully for email={}",
                normalizedEmail
        );

        try {

            log.info(
                    "Calling EmailService for email={}",
                    normalizedEmail
            );

            emailService.sendOtp(
                    normalizedEmail,
                    otp
            );

            log.info(
                    "OTP email sent successfully for email={}",
                    normalizedEmail
            );

        } catch (Exception e) {

            log.error(
                    "OTP EMAIL FAILED for email={}. Error={}",
                    normalizedEmail,
                    e.getMessage(),
                    e
            );

            throw e;
        }
    }

    @Transactional
    public String verify(
            String rawEmail,
            String submittedOtp
    ) {

        String normalizedEmail = normalize(rawEmail);

        log.info(
                "OTP verification requested for email={}",
                normalizedEmail
        );

        EmailOtp record = otps
                .findTopByEmailOrderByCreatedAtDesc(
                        normalizedEmail
                )
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "Request a new OTP"
                        )
                );

        if (record.isVerified()
                && record.getVerificationTokenHash() != null) {

            throw new IllegalStateException(
                    "OTP is already verified"
            );
        }

        if (Instant.now().isAfter(record.getExpiresAt())) {

            log.warn(
                    "Expired OTP attempted for email={}",
                    normalizedEmail
            );

            throw new IllegalArgumentException(
                    "OTP expired"
            );
        }

        if (record.getAttempts() >= MAX_ATTEMPTS) {

            log.warn(
                    "Maximum OTP attempts exceeded for email={}",
                    normalizedEmail
            );

            throw new IllegalArgumentException(
                    "Maximum attempts exceeded"
            );
        }

        record.setAttempts(
                record.getAttempts() + 1
        );

        if (!passwordEncoder.matches(
                submittedOtp,
                record.getOtpHash()
        )) {

            otps.save(record);

            log.warn(
                    "Invalid OTP submitted for email={}",
                    normalizedEmail
            );

            throw new IllegalArgumentException(
                    "Invalid OTP"
            );
        }

        String verificationToken =
                UUID.randomUUID().toString();

        record.setVerified(true);

        record.setVerificationTokenHash(
                passwordEncoder.encode(
                        verificationToken
                )
        );

        otps.save(record);

        log.info(
                "OTP successfully verified for email={}",
                normalizedEmail
        );

        return verificationToken;
    }

    @Transactional(readOnly = true)
    public boolean validToken(
            String rawEmail,
            String verificationToken
    ) {

        if (verificationToken == null
                || verificationToken.isBlank()) {
            return false;
        }

        return otps
                .findTopByEmailOrderByCreatedAtDesc(
                        normalize(rawEmail)
                )
                .filter(EmailOtp::isVerified)
                .filter(record ->
                        record.getVerificationTokenHash()
                                != null
                )
                .filter(record ->
                        Instant.now().isBefore(
                                record.getExpiresAt()
                        )
                )
                .filter(record ->
                        passwordEncoder.matches(
                                verificationToken,
                                record.getVerificationTokenHash()
                        )
                )
                .isPresent();
    }
}