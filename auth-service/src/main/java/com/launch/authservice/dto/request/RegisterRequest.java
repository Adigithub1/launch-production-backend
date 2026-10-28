package com.launch.authservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    @Pattern(
        regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$",
        flags = Pattern.Flag.CASE_INSENSITIVE,
        message = "Only Gmail addresses are supported"
    )
    private String email;

    @NotBlank(message = "Password is required")
    @Size(
        min = 8,
        max = 72,
        message = "Password must contain 8 to 72 characters"
    )
    private String password;

    @NotBlank(message = "Role is required")
    private String role;

    @NotBlank(message = "Device ID is required")
    private String deviceId;

    private String deviceName;

    @NotBlank(message = "Verification token is required")
    private String verificationToken;

    private String plan;

    private String paymentStatus;

    public RegisterRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(
            String verificationToken
    ) {
        this.verificationToken =
                verificationToken;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(
            String paymentStatus
    ) {
        this.paymentStatus =
                paymentStatus;
    }
}