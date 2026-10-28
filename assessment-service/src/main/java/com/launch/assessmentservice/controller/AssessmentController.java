package com.launch.assessmentservice.controller;

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

import com.launch.assessmentservice.dto.request.AssessmentRequest;
import com.launch.assessmentservice.dto.response.AssessmentResponse;
import com.launch.assessmentservice.service.AssessmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/assessment")
@RequiredArgsConstructor
public class AssessmentController {
	private final AssessmentService service;

	@PostMapping
	ResponseEntity<AssessmentResponse> create(@Valid @RequestBody AssessmentRequest r) {
		return ResponseEntity.status(201).body(service.create(r));
	}

	@GetMapping("/{id}")
	AssessmentResponse get(@PathVariable Long id) {
		return service.get(id);
	}

	@GetMapping
	List<AssessmentResponse> list() {
		return service.list();
	}

	@PutMapping("/{id}")
	AssessmentResponse update(@PathVariable Long id, @Valid @RequestBody AssessmentRequest r) {
		return service.update(id, r);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Long id) {
		service.delete(id);
	}
}