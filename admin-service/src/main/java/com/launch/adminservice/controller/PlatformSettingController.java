package com.launch.adminservice.controller;

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

import com.launch.adminservice.dto.request.PlatformSettingRequest;
import com.launch.adminservice.dto.response.PlatformSettingResponse;
import com.launch.adminservice.service.PlatformSettingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class PlatformSettingController {
	private final PlatformSettingService service;

	@PostMapping
	ResponseEntity<PlatformSettingResponse> create(@Valid @RequestBody PlatformSettingRequest r) {
		return ResponseEntity.status(201).body(service.create(r));
	}

	@GetMapping("/{id}")
	PlatformSettingResponse get(@PathVariable Long id) {
		return service.get(id);
	}

	@GetMapping
	List<PlatformSettingResponse> list() {
		return service.list();
	}

	@PutMapping("/{id}")
	PlatformSettingResponse update(@PathVariable Long id, @Valid @RequestBody PlatformSettingRequest r) {
		return service.update(id, r);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Long id) {
		service.delete(id);
	}
}