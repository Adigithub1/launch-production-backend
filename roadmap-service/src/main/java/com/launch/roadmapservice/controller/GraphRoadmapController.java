package com.launch.roadmapservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.launch.roadmapservice.dto.request.GraphDtos.GenerateGraphRequest;
import com.launch.roadmapservice.dto.request.GraphDtos.GraphResponse;
import com.launch.roadmapservice.dto.request.GraphDtos.StatusRequest;
import com.launch.roadmapservice.service.GraphRoadmapService;

@RestController
@RequestMapping("/api/roadmap/student/{studentId}/graph")
@CrossOrigin(origins = "http://localhost:5173")
public class GraphRoadmapController {

	private final GraphRoadmapService service;

	public GraphRoadmapController(GraphRoadmapService service) {

		this.service = service;
	}

	/*
	 * Get an existing graph only.
	 *
	 * Do NOT silently generate Software Developer or any other roadmap here.
	 */
	@GetMapping
	public GraphResponse get(@PathVariable Long studentId, @RequestParam String roadmapKey) {

		return service.get(studentId, roadmapKey);
	}

	/*
	 * Explicit creation / regeneration.
	 *
	 * Frontend sends:
	 *
	 * roadmapKey targetRole faculty degree branch currentSkills
	 */
	@PostMapping("/generate")
	public ResponseEntity<GraphResponse> generate(@PathVariable Long studentId,
			@RequestBody GenerateGraphRequest request) {

		if (request == null) {
			throw new IllegalArgumentException("Roadmap generation request is required.");
		}

		if (request.targetRole() == null || request.targetRole().isBlank()) {

			throw new IllegalArgumentException("Target role is required.");
		}

		GraphResponse response = service.generate(studentId, request);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/*
	 * Update learning/progress state.
	 */
	@PutMapping("/nodes/{nodeKey}/status")
	public GraphResponse status(@PathVariable Long studentId, @PathVariable String nodeKey,
			@RequestParam String roadmapKey, @RequestBody StatusRequest request) {

		return service.updateStatus(studentId, roadmapKey, nodeKey, request.status());
	}
}