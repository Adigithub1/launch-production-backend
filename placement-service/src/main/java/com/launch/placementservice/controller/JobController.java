package com.launch.placementservice.controller;

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

import com.launch.placementservice.dto.request.JobRequest;
import com.launch.placementservice.dto.response.JobResponse;
import com.launch.placementservice.service.JobService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/placement")
@RequiredArgsConstructor
public class JobController {
	private final JobService service;

	@PostMapping
	ResponseEntity<JobResponse> create(@Valid @RequestBody JobRequest r) {
		return ResponseEntity.status(201).body(service.create(r));
	}

	@GetMapping("/{id}")
	JobResponse get(@PathVariable Long id) {
		return service.get(id);
	}

	@GetMapping
	List<JobResponse> list() {
		return service.list();
	}

	@PutMapping("/{id}")
	JobResponse update(@PathVariable Long id, @Valid @RequestBody JobRequest r) {
		return service.update(id, r);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Long id) {
		service.delete(id);
	}
}