package com.launch.learningservice.dto;

import java.util.List;

public final class LearningDtos {
	private LearningDtos() {
	}

	public record RoadmapGraph(Long studentRoadmapId, Long studentId, String roadmapKey, String targetRole,
			String label, Integer overallProgress, List<RoadmapNode> nodes, List<RoadmapEdge> edges) {
	}

	public record RoadmapNode(String nodeKey, String title, String description, String nodeType, String phaseTitle,
			Integer positionX, Integer positionY, Integer width, Integer height, Boolean clickable, Boolean optional,
			String status) {
	}

	public record RoadmapEdge(String edgeKey, String sourceNodeKey, String targetNodeKey) {
	}

	public record CourseResponse(Long id, String nodeKey, String title, String category, String difficulty,
			Integer durationMinutes, Integer sequenceNumber, Integer progress, String status, String sourceType) {
	}

	public record LearningPathResponse(Long studentId, Long studentRoadmapId, String roadmapKey, String targetRole,
			Integer overallProgress, List<CourseResponse> courses) {
	}

	public record TopicResponse(String nodeKey, String title, String overview, List<String> learningOutcomes,
			String contentMarkdown, List<VideoResponse> videos) {
	}

	public record VideoResponse(Long id, String title, String provider, String sourceType, String url,
			Integer durationMinutes) {
	}

	public record ProgressRequest(Integer progress, Boolean quizPassed) {
	}

	public record AiRequest(String question) {
	}

	public record AiResponse(String answer, List<String> followUpQuestions) {
	}
}
