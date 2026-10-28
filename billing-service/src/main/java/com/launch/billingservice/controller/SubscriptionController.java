package com.launch.billingservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.launch.billingservice.dto.request.SubscriptionRequest;
import com.launch.billingservice.dto.response.SubscriptionResponse;
import com.launch.billingservice.service.SubscriptionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/billing")
@RequiredArgsConstructor
public class SubscriptionController {
	private final SubscriptionService service;

	@PostMapping
	ResponseEntity<SubscriptionResponse> create(@Valid @RequestBody SubscriptionRequest r) {
		return ResponseEntity.status(201).body(service.create(r));
	}

	@GetMapping("/{id}")
	SubscriptionResponse get(@PathVariable Long id) {
		return service.get(id);
	}

	@GetMapping
	List<SubscriptionResponse> list() {
		return service.list();
	}

	@PutMapping("/{id}")
	SubscriptionResponse update(@PathVariable Long id, @Valid @RequestBody SubscriptionRequest r) {
		return service.update(id, r);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Long id) {
		service.delete(id);
	}
}