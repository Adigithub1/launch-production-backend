package com.launch.authservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;

@Service
public class EmailService {

    private static final Logger log =
            LoggerFactory.getLogger(EmailService.class);

    private final Resend resend;
    private final String fromEmail;

    public EmailService(
            @Value("${resend.api-key}") String apiKey,
            @Value("${resend.from-email}") String fromEmail) {

        this.resend = new Resend(apiKey);
        this.fromEmail = fromEmail;

        log.info("Resend EmailService initialized with sender={}", fromEmail);
    }

    public void sendOtp(String toEmail, String otp) {

        log.info(
                "Attempting OTP email through Resend: from={}, to={}",
                fromEmail,
                toEmail
        );

        CreateEmailOptions email = CreateEmailOptions.builder()
                .from(fromEmail)
                .to(toEmail)
                .subject("LAUNCH Verification Code")
                .html("""
                    <div style="font-family: Arial, sans-serif;">
                        <h2>LAUNCH Verification</h2>

                        <p>Your verification code is:</p>

                        <h1>%s</h1>

                        <p>This code expires in 5 minutes.</p>

                        <p>
                            If you did not request this code,
                            please ignore this email.
                        </p>
                    </div>
                    """.formatted(otp))
                .build();

        try {

            CreateEmailResponse response =
                    resend.emails().send(email);

            log.info(
                    "Resend accepted OTP email successfully. emailId={}",
                    response.getId()
            );

        } catch (ResendException e) {

            log.error(
                    "RESEND ERROR sending OTP to {}: {}",
                    toEmail,
                    e.getMessage(),
                    e
            );

            throw new IllegalStateException(
                    "Unable to send OTP email: " + e.getMessage(),
                    e
            );
        }
    }
}