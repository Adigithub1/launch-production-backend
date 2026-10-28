package com.launch.authservice.dto.response;

public class UserResponse {

    private Long id;
    private String email;
    private String role;
    private String status;
    private boolean emailVerified;

    public UserResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(
            String email
    ) {
        this.email = email;
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

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(
            boolean emailVerified
    ) {
        this.emailVerified =
                emailVerified;
    }
}