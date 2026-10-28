package com.launch.billingservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public final class PaymentDtos {
	private PaymentDtos() {
	}

	public record CreateOrderRequest(@NotBlank String planCode, @Email @NotBlank String email,
			@NotBlank String verificationToken) {
	}

	public record CreateOrderResponse(String keyId, String orderId, long amount, String currency,
			String paymentReference) {
	}

	public record VerifyRequest(@NotBlank String razorpayOrderId, @NotBlank String razorpayPaymentId,
			@NotBlank String razorpaySignature, @Email @NotBlank String email, @NotBlank String verificationToken) {
	}

	public record VerifyResponse(String status, String paymentReference) {
	}

	public record TokenValidationRequest(@Email @NotBlank String email, @NotBlank String verificationToken) {
	}

	public record TokenValidationResponse(boolean valid) {
	}
}
