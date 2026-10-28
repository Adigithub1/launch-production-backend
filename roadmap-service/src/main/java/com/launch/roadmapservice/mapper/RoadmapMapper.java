package com.launch.roadmapservice.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.launch.roadmapservice.dto.request.RoadmapRequest;
import com.launch.roadmapservice.dto.response.PhaseResponse;
import com.launch.roadmapservice.dto.response.RoadmapResponse;
import com.launch.roadmapservice.entity.Roadmap;
import com.launch.roadmapservice.entity.RoadmapPhase;

@Component
public class RoadmapMapper {

	public Roadmap toEntity(RoadmapRequest request) {

		if (request == null) {
			throw new IllegalArgumentException("RoadmapRequest cannot be null");
		}

		Roadmap roadmap = new Roadmap();

		roadmap.setStudentId(request.getStudentId());
		roadmap.setVersion(request.getVersion());
		roadmap.setTargetRole(request.getTargetRole());
		roadmap.setLabel(request.getLabel());
		roadmap.setStatus(request.getStatus());

		return roadmap;
	}

	public void update(Roadmap roadmap, RoadmapRequest request) {

		if (roadmap == null) {
			throw new IllegalArgumentException("Roadmap cannot be null");
		}

		if (request == null) {
			throw new IllegalArgumentException("RoadmapRequest cannot be null");
		}

		roadmap.setStudentId(request.getStudentId());
		roadmap.setVersion(request.getVersion());
		roadmap.setTargetRole(request.getTargetRole());
		roadmap.setLabel(request.getLabel());
		roadmap.setStatus(request.getStatus());
	}

	public RoadmapResponse toResponse(Roadmap roadmap, List<RoadmapPhase> phases) {

		if (roadmap == null) {
			throw new IllegalArgumentException("Roadmap cannot be null");
		}

		if (phases == null) {
			phases = Collections.emptyList();
		}

		List<PhaseResponse> phaseResponses = new ArrayList<>();

		List<String> allSkills = new ArrayList<>();

		int totalProgress = 0;

		for (RoadmapPhase roadmapPhase : phases) {

			if (roadmapPhase == null) {
				continue;
			}

			List<String> phaseSkills = roadmapPhase.getSkills();

			if (phaseSkills == null) {
				phaseSkills = Collections.emptyList();
			}

			PhaseResponse phaseResponse = new PhaseResponse();

			phaseResponse.setId(roadmapPhase.getId());

			phaseResponse.setPhaseNumber(roadmapPhase.getPhaseNumber());

			phaseResponse.setPeriod("Phase " + roadmapPhase.getPhaseNumber());

			phaseResponse.setPhase(roadmapPhase.getTitle());

			phaseResponse.setStatus(roadmapPhase.getStatus());

			phaseResponse.setProgress(roadmapPhase.getProgress());

			phaseResponse.setSkills(new ArrayList<>(phaseSkills));

			phaseResponses.add(phaseResponse);

			for (String skill : phaseSkills) {

				if (skill != null && !skill.isBlank() && !allSkills.contains(skill)) {

					allSkills.add(skill);
				}
			}

			Integer progress = roadmapPhase.getProgress();

			if (progress != null) {
				totalProgress += progress;
			}
		}

		int overallProgress = 0;

		if (!phases.isEmpty()) {
			overallProgress = (int) Math.round((double) totalProgress / phases.size());
		}

		RoadmapResponse response = new RoadmapResponse();

		response.setId(roadmap.getId());

		response.setStudentId(roadmap.getStudentId());

		response.setVersion(roadmap.getVersion());

		response.setTargetRole(roadmap.getTargetRole());

		response.setLabel(roadmap.getLabel());

		response.setStatus(roadmap.getStatus());

		response.setReadiness(overallProgress);

		response.setOverallProgress(overallProgress);

		response.setCurrentSkills(new ArrayList<>());

		response.setMissingSkills(allSkills);

		response.setPhases(phaseResponses);

		response.setProjects(defaultProjects(roadmap.getTargetRole()));

		return response;
	}

	private List<String> defaultProjects(String role) {

		String safeRole = role;

		if (safeRole == null || safeRole.isBlank()) {

			safeRole = "Career";
		}

		return List.of(safeRole + " Starter Project", safeRole + " Portfolio Project", "Capstone for " + safeRole);
	}
}