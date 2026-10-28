package com.launch.roadmapservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.launch.roadmapservice.dto.request.GenerateRoadmapRequest;
import com.launch.roadmapservice.dto.request.RoadmapRequest;
import com.launch.roadmapservice.dto.response.RoadmapResponse;
import com.launch.roadmapservice.service.RoadmapService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roadmap")
@CrossOrigin(origins = "http://localhost:5173")
public class RoadmapController {

	private final RoadmapService service;

	public RoadmapController(RoadmapService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<RoadmapResponse> create(@Valid @RequestBody RoadmapRequest request) {

		RoadmapResponse response = service.create(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{id}")
	public RoadmapResponse get(@PathVariable Long id) {

		return service.get(id);
	}

	@GetMapping
	public List<RoadmapResponse> list() {

		return service.list();
	}

	@PutMapping("/{id}")
	public RoadmapResponse update(@PathVariable Long id, @Valid @RequestBody RoadmapRequest request) {

		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {

		service.delete(id);
	}

	@GetMapping("/student/{studentId}")
	public RoadmapResponse getByStudent(@PathVariable Long studentId) {

		return service.getByStudent(studentId);
	}

	@PostMapping("/student/{studentId}/generate")
	public RoadmapResponse generate(@PathVariable Long studentId, @Valid @RequestBody GenerateRoadmapRequest request) {

		return service.generate(studentId, request);
	}

	@PostMapping("/{roadmapId}/activate")
	public RoadmapResponse activate(@PathVariable Long roadmapId) {
		return service.activate(roadmapId);
	}

}