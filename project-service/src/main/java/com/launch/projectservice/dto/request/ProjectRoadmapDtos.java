package com.launch.projectservice.dto.request;

import java.util.List;

public final class ProjectRoadmapDtos {
	private ProjectRoadmapDtos() {
	}

	public record RoadmapGraph(Long id, Long studentId, String targetRole, String label, Integer overallProgress,
			List<RoadmapNode> nodes, List<RoadmapEdge> edges) {
	}

	public record RoadmapNode(Long id, String nodeKey, String title, String description, String nodeType, String status,
			String phaseTitle, Integer positionX, Integer positionY, List<String> learningOutcomes,
			List<Object> resources) {
	}

	public record RoadmapEdge(String edgeKey, String sourceNodeKey, String targetNodeKey, String status) {
	}

	public record ProjectPathResponse(Long studentId, Long studentRoadmapId, String roadmapKey, String targetRole,
			Integer overallProgress, List<com.launch.projectservice.dto.response.ProjectResponse> projects) {
	}

	public record ProgressRequest(Integer progress, Boolean completed) {
	}
}
