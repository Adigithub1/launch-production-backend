package com.launch.roadmapservice.dto.request;

import java.util.List;

public final class GraphDtos {
	private GraphDtos() {
	}

	public record GenerateGraphRequest(String roadmapKey, String targetRole, String faculty, String degree,
			String branch, List<String> currentSkills) {
	}

	public record StatusRequest(String status) {
	}

	public record ResourceResponse(Long id, String title, String resourceType, String provider, String url,
			Integer durationMinutes) {
	}

	public record NodeResponse(Long id, String nodeKey, String title, String description, String nodeType,
			String status, String phaseTitle, Integer positionX, Integer positionY, List<String> learningOutcomes,
			List<ResourceResponse> resources) {
	}

	public record EdgeResponse(String edgeKey, String sourceNodeKey, String targetNodeKey, String status) {
	}

	public record GraphResponse(Long id, Long studentId, String targetRole, String label, Integer overallProgress,
			List<NodeResponse> nodes, List<EdgeResponse> edges) {
	}

	public record ProfileCompletedEvent(Long studentId, String faculty, String degree, String branch, String targetRole,
			List<String> currentSkills) {
	}
}
