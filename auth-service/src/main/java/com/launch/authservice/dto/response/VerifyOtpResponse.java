package com.launch.authservice.dto.response;

public class VerifyOtpResponse {

    private String verificationToken;

    public VerifyOtpResponse() {
    }

    public VerifyOtpResponse(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }
}