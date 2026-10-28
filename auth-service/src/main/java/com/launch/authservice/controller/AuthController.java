package com.launch.authservice.controller;

import com.launch.authservice.dto.request.*;
import com.launch.authservice.dto.response.*;
import com.launch.authservice.service.AuthService;
import com.launch.authservice.service.OtpService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final OtpService otp;
	private final AuthService auth;

	public AuthController(OtpService otp, AuthService auth) {
		this.otp = otp;
		this.auth = auth;
	}

	@PostMapping("/otp/request")
	public Map<String, String> request(@Valid @RequestBody RequestOtpRequest r) {

		otp.request(r.getEmail());

		return Map.of("message", "Verification code sent");
	}

	@PostMapping("/otp/verify")
	public VerifyOtpResponse verify(@Valid @RequestBody VerifyOtpRequest r) {

		return new VerifyOtpResponse(otp.verify(r.getEmail(), r.getOtp()));
	}

	@PostMapping("/register")
	public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest r) {

		return ResponseEntity.status(HttpStatus.CREATED).body(auth.register(r));
	}

	@PostMapping("/login")
	public LoginResponse login(@Valid @RequestBody LoginRequest r) {

		return auth.login(r);
	}
	
	@PostMapping("/validate-token")
	public ResponseEntity<VerificationTokenResponse> validateToken(
	        @Valid
	        @RequestBody
	        ValidateVerificationTokenRequest request) {

	    boolean valid = otp.validToken(
	            request.getEmail(),
	            request.getVerificationToken()
	    );

	    return ResponseEntity.ok(
	            new VerificationTokenResponse(valid)
	    );
	}
}