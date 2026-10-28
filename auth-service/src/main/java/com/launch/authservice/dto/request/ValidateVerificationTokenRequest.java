package com.launch.authservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ValidateVerificationTokenRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;

    @NotBlank(message = "Verification token is required")
    private String verificationToken;

    public ValidateVerificationTokenRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(
            String verificationToken) {
        this.verificationToken = verificationToken;
    }
}