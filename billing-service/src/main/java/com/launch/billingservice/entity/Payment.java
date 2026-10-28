package com.launch.billingservice.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments", indexes = @Index(name = "idx_payment_email", columnList = "email"))
public class Payment {
	@Id
	private UUID id;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String planCode;
	@Column(nullable = false)
	private long amountPaise;
	@Column(nullable = false, length = 3)
	private String currency;
	@Column(nullable = false, unique = true)
	private String receipt;
	@Column(nullable = false, unique = true)
	private String razorpayOrderId;
	@Column(unique = true)
	private String razorpayPaymentId;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PaymentStatus status;
	@Column(nullable = false)
	private Instant createdAt;
	@Column(nullable = false)
	private Instant updatedAt;

	protected Payment() {
	}

	public Payment(String email, String planCode, long amountPaise, String currency, String receipt, String orderId) {
		this.id = UUID.randomUUID();
		this.email = email;
		this.planCode = planCode;
		this.amountPaise = amountPaise;
		this.currency = currency;
		this.receipt = receipt;
		this.razorpayOrderId = orderId;
		this.status = PaymentStatus.CREATED;
		this.createdAt = Instant.now();
		this.updatedAt = this.createdAt;
	}

	public void paid(String id) {
		this.razorpayPaymentId = id;
		this.status = PaymentStatus.PAID;
		this.updatedAt = Instant.now();
	}

	public void failed() {
		if (status != PaymentStatus.PAID) {
			status = PaymentStatus.FAILED;
			updatedAt = Instant.now();
		}
	}

	public UUID getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPlanCode() {
		return planCode;
	}

	public long getAmountPaise() {
		return amountPaise;
	}

	public String getCurrency() {
		return currency;
	}

	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}

	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}

	public PaymentStatus getStatus() {
		return status;
	}
}
