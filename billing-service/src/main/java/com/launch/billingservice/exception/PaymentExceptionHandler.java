package com.launch.billingservice.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PaymentExceptionHandler {
	@ExceptionHandler(PaymentException.class)
	ResponseEntity<Map<String, String>> payment(PaymentException e) {
		return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
	}

	@ExceptionHandler(SecurityException.class)
	ResponseEntity<Map<String, String>> security(SecurityException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", e.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<Map<String, String>> validation(MethodArgumentNotValidException e) {
		return ResponseEntity.badRequest().body(Map.of("message", "Invalid payment request"));
	}
}
