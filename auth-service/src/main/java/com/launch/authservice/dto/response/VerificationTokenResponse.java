package com.launch.authservice.dto.response;

public class VerificationTokenResponse {

    private boolean valid;

    public VerificationTokenResponse() {
    }

    public VerificationTokenResponse(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}