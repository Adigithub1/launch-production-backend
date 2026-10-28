package com.launch.notificationservice.controller;

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

import com.launch.notificationservice.dto.request.NotificationRequest;
import com.launch.notificationservice.dto.response.NotificationResponse;
import com.launch.notificationservice.service.NotificationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {
	private final NotificationService service;

	@PostMapping
	ResponseEntity<NotificationResponse> create(@Valid @RequestBody NotificationRequest r) {
		return ResponseEntity.status(201).body(service.create(r));
	}

	@GetMapping("/{id}")
	NotificationResponse get(@PathVariable Long id) {
		return service.get(id);
	}

	@GetMapping
	List<NotificationResponse> list() {
		return service.list();
	}

	@PutMapping("/{id}")
	NotificationResponse update(@PathVariable Long id, @Valid @RequestBody NotificationRequest r) {
		return service.update(id, r);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Long id) {
		service.delete(id);
	}
}