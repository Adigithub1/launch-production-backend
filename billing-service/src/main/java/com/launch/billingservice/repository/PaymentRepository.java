package com.launch.billingservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.launch.billingservice.entity.Payment;


public interface PaymentRepository extends JpaRepository<Payment, UUID> {
	Optional<Payment> findByRazorpayOrderId(String orderId);

	Optional<Payment> findByRazorpayPaymentId(String paymentId);
}
