package com.launch.authservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;

@Service
public class EmailService {

    private final Resend resend;
    private final String fromEmail;

    public EmailService(
            @Value("${resend.api-key}") String apiKey,
            @Value("${resend.from-email}") String fromEmail) {

        this.resend = new Resend(apiKey);
        this.fromEmail = fromEmail;
    }

    public void sendOtp(String toEmail, String otp) {

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

                        <p>If you did not request this code, ignore this email.</p>
                    </div>
                    """.formatted(otp))
                .build();

        try {

            resend.emails().send(email);

        } catch (ResendException e) {

            throw new IllegalStateException(
                    "Unable to send OTP email",
                    e
            );
        }
    }
}