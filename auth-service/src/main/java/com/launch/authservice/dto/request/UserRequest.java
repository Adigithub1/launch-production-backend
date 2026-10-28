package com.launch.authservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
	@jakarta.validation.constraints.NotNull
	private String email;
	@jakarta.validation.constraints.NotNull
	private String passwordHash;
	@jakarta.validation.constraints.NotNull
	private String role;
	@jakarta.validation.constraints.NotNull
	private String status;
	@jakarta.validation.constraints.NotNull
	private String deviceId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}