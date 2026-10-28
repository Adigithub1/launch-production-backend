package com.launch.interviewservice.controller;

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

import com.launch.interviewservice.dto.request.InterviewSessionRequest;
import com.launch.interviewservice.dto.response.InterviewSessionResponse;
import com.launch.interviewservice.service.InterviewSessionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/interview")
@RequiredArgsConstructor
public class InterviewSessionController {
	private final InterviewSessionService service;

	@PostMapping
	ResponseEntity<InterviewSessionResponse> create(@Valid @RequestBody InterviewSessionRequest r) {
		return ResponseEntity.status(201).body(service.create(r));
	}

	@GetMapping("/{id}")
	InterviewSessionResponse get(@PathVariable Long id) {
		return service.get(id);
	}

	@GetMapping
	List<InterviewSessionResponse> list() {
		return service.list();
	}

	@PutMapping("/{id}")
	InterviewSessionResponse update(@PathVariable Long id, @Valid @RequestBody InterviewSessionRequest r) {
		return service.update(id, r);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Long id) {
		service.delete(id);
	}
}