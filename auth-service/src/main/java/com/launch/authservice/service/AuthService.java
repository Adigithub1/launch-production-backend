package com.launch.authservice.service;

import com.launch.authservice.dto.request.LoginRequest;
import com.launch.authservice.dto.request.RegisterRequest;
import com.launch.authservice.dto.response.LoginResponse;
import com.launch.authservice.dto.response.UserResponse;
import com.launch.authservice.entity.User;
import com.launch.authservice.repository.UserRepository;
import com.launch.authservice.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Locale;

@Service
public class AuthService {

    private final UserRepository users;
    private final OtpService otp;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public AuthService(
            UserRepository users,
            OtpService otp,
            PasswordEncoder encoder,
            JwtService jwt
    ) {
        this.users = users;
        this.otp = otp;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    private String normalizeEmail(String email) {
        return email
                .trim()
                .toLowerCase(Locale.ROOT);
    }

    @Transactional
    public UserResponse register(
            RegisterRequest request
    ) {
        String email =
                normalizeEmail(request.getEmail());

        if (!otp.validToken(
                email,
                request.getVerificationToken()
        )) {
            throw new IllegalStateException(
                    "Email verification is missing or expired"
            );
        }

        if (users.existsByEmailIgnoreCase(email)) {
            throw new IllegalStateException(
                    "Email is already registered"
            );
        }

        User user = new User();

        user.setEmail(email);

        user.setPasswordHash(
                encoder.encode(
                        request.getPassword()
                )
        );

        user.setRole(request.getRole());
        user.setStatus("ACTIVE");
        user.setDeviceId(
                request.getDeviceId()
        );
        user.setDeviceName(
                request.getDeviceName()
        );
        user.setEmailVerified(true);
        user.setCreatedAt(Instant.now());

        User saved = users.save(user);

        UserResponse response =
                new UserResponse();

        response.setId(saved.getId());
        response.setEmail(saved.getEmail());
        response.setRole(saved.getRole());
        response.setStatus(saved.getStatus());
        response.setEmailVerified(
                saved.isEmailVerified()
        );

        return response;
    }

    @Transactional
    public LoginResponse login(
            LoginRequest request
    ) {
        String email =
                normalizeEmail(request.getEmail());

        User user = users
                .findByEmailIgnoreCase(email)
                .orElseThrow(
                    () -> new IllegalArgumentException(
                        "Invalid email or password"
                    )
                );

        if (!encoder.matches(
                request.getPassword(),
                user.getPasswordHash()
        )) {
            throw new IllegalArgumentException(
                    "Invalid email or password"
            );
        }

        if (user.getDeviceId() != null
                && !user.getDeviceId().equals(
                    request.getDeviceId()
                )) {
            return new LoginResponse(
                    null,
                    user.getId(),
                    user.getRole(),
                    "DEVICE_BLOCKED"
            );
        }

        if (user.getDeviceId() == null) {
            user.setDeviceId(
                    request.getDeviceId()
            );

            user.setDeviceName(
                    request.getDeviceName()
            );

            users.save(user);
        }

        String accessToken = jwt.create(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return new LoginResponse(
                accessToken,
                user.getId(),
                user.getRole(),
                "AUTHENTICATED"
        );
    }
}