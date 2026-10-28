package com.launch.authservice.dto.response;

public class LoginResponse {

    private String accessToken;
    private Long userId;
    private String role;
    private String status;

    public LoginResponse() {
    }

    public LoginResponse(
            String accessToken,
            Long userId,
            String role,
            String status
    ) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.role = role;
        this.status = status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(
            String accessToken
    ) {
        this.accessToken = accessToken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(
            Long userId
    ) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(
            String role
    ) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(
            String status
    ) {
        this.status = status;
    }
}