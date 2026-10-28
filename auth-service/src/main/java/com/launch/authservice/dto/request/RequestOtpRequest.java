package com.launch.authservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RequestOtpRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    @Pattern(
        regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$",
        flags = Pattern.Flag.CASE_INSENSITIVE,
        message = "Only Gmail addresses are supported"
    )
    private String email;

    public RequestOtpRequest() {
    }

    public RequestOtpRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}