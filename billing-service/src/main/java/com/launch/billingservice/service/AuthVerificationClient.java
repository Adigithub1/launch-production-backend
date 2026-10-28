package com.launch.billingservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.launch.billingservice.exception.PaymentException;

@Component
public class AuthVerificationClient {

	private final RestClient client;

	public AuthVerificationClient(RestClient.Builder builder,
			@Value("${launch.auth-service-url}") String authServiceUrl) {

		System.out.println("AUTH SERVICE URL = " + authServiceUrl);

		this.client = builder.baseUrl(authServiceUrl).build();
	}

	public void validate(String email, String verificationToken) {

		try {

			System.out.println(">>> CALLING AUTH VALIDATE TOKEN <<<");

			System.out.println("Email = " + email);

			TokenValidationResponse response = client.post().uri("/api/auth/validate-token")
					.body(new TokenValidationRequest(email, verificationToken)).retrieve()
					.body(TokenValidationResponse.class);

			System.out.println("Auth response = " + response);

			if (response == null) {
				throw new PaymentException("Auth-service returned empty response");
			}

			if (!response.valid()) {
				throw new SecurityException("OTP verification token is invalid or expired");
			}

		} catch (SecurityException e) {

			throw e;

		} catch (Exception e) {

			System.err.println("AUTH VALIDATION FAILED");

			System.err.println("Exception = " + e.getClass().getName());

			System.err.println("Message = " + e.getMessage());

			e.printStackTrace();

			throw new PaymentException("Unable to validate OTP verification token with auth-service");
		}
	}

	public record TokenValidationRequest(String email, String verificationToken) {
	}

	public record TokenValidationResponse(boolean valid) {
	}
}