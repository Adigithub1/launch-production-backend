package com.launch.authservice.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender sender;

    public EmailService(JavaMailSender sender) {
        this.sender = sender;
    }

    public void sendOtp(String email, String otp) {

        System.out.println("Sending OTP to: " + email);
        System.out.println("OTP generated: " + otp);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Verify your LAUNCH account");

        message.setText(
                "Your LAUNCH verification code is "
                        + otp
                        + ". It expires in 5 minutes."
        );

        sender.send(message);

        System.out.println("EMAIL SENT SUCCESSFULLY");
    }
}