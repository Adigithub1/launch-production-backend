package com.launch.billingservice.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.launch.billingservice.dto.request.PaymentDtos;
import com.launch.billingservice.service.PaymentService;
import com.razorpay.RazorpayException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {
	private final PaymentService service;

	public PaymentController(PaymentService service) {
		this.service = service;
	}

	@PostMapping("/orders")
	public PaymentDtos.CreateOrderResponse create(@Valid @RequestBody PaymentDtos.CreateOrderRequest r)
			throws RazorpayException {
		return service.create(r);
	}

	@PostMapping("/verify")
	public PaymentDtos.VerifyResponse verify(@Valid @RequestBody PaymentDtos.VerifyRequest r) throws RazorpayException {
		return service.verify(r);
	}
}
