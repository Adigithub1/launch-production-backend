package com.launch.billingservice.service;

import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.launch.billingservice.dto.request.PaymentDtos;
import com.launch.billingservice.entity.Payment;
import com.launch.billingservice.entity.PaymentStatus;
import com.launch.billingservice.exception.PaymentException;
import com.launch.billingservice.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

@Service
public class PaymentService {
	private final RazorpayClient razorpay;
	private final com.launch.billingservice.repository.PaymentRepository repo;
	private final AuthVerificationClient auth;
	private final String keyId;
	private final String keySecret;

	public PaymentService(PaymentRepository repo, AuthVerificationClient auth,
			@Value("${razorpay.key-id}") String keyId, @Value("${razorpay.key-secret}") String keySecret)
			throws RazorpayException {
		this.repo = repo;
		this.auth = auth;
		this.keyId = keyId;
		this.keySecret = keySecret;
		this.razorpay = new RazorpayClient(keyId, keySecret);
	}

	private long price(String plan) {
		return switch (plan) {
		case "PREMIUM" -> 49900L;
		case "PREMIUM_PLUS" -> 99900L;
		default -> throw new PaymentException("Unsupported paid plan: " + plan);
		};
	}

	@Transactional
	public PaymentDtos.CreateOrderResponse create(PaymentDtos.CreateOrderRequest request) throws RazorpayException {
		String email = request.email().trim().toLowerCase();
		auth.validate(email, request.verificationToken());
		long amount = price(request.planCode());
		String receipt = "launch_" + UUID.randomUUID().toString().replace("-", "").substring(0, 20);
		JSONObject body = new JSONObject().put("amount", amount).put("currency", "INR").put("receipt", receipt)
				.put("notes", new JSONObject().put("email", email).put("plan", request.planCode()));
		Order order = razorpay.orders.create(body);
		String orderId = order.get("id");
		Payment p = repo.save(new Payment(email, request.planCode(), amount, "INR", receipt, orderId));
		return new PaymentDtos.CreateOrderResponse(keyId, orderId, amount, "INR", p.getId().toString());
	}

	@Transactional
	public PaymentDtos.VerifyResponse verify(PaymentDtos.VerifyRequest request) throws RazorpayException {
		String email = request.email().trim().toLowerCase();
		auth.validate(email, request.verificationToken());
		Payment p = repo.findByRazorpayOrderId(request.razorpayOrderId())
				.orElseThrow(() -> new PaymentException("Payment order not found"));
		if (!p.getEmail().equalsIgnoreCase(email))
			throw new SecurityException("Payment order does not belong to this registration");
		if (p.getStatus() == PaymentStatus.PAID)
			return new PaymentDtos.VerifyResponse("PAID", p.getId().toString());
		JSONObject attrs = new JSONObject().put("razorpay_order_id", request.razorpayOrderId())
				.put("razorpay_payment_id", request.razorpayPaymentId())
				.put("razorpay_signature", request.razorpaySignature());
		if (!Utils.verifyPaymentSignature(attrs, keySecret))
			throw new SecurityException("Invalid Razorpay payment signature");
		p.paid(request.razorpayPaymentId());
		return new PaymentDtos.VerifyResponse("PAID", p.getId().toString());
	}

	@Transactional
	public void captured(String orderId, String paymentId) {
		repo.findByRazorpayOrderId(orderId).ifPresent(p -> {
			if (p.getStatus() != PaymentStatus.PAID)
				p.paid(paymentId);
		});
	}

	@Transactional
	public void failed(String orderId) {
		repo.findByRazorpayOrderId(orderId).ifPresent(Payment::failed);
	}
}
