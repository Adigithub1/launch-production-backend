package com.launch.roadmapservice.event;

import java.util.UUID;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.launch.roadmapservice.dto.request.GenerateRoadmapRequest;
import com.launch.roadmapservice.repository.ProcessedEventRepository;
import com.launch.roadmapservice.service.RoadmapService;

@Component
public class StudentProfileCompletedListener {
	private final ObjectMapper mapper;
	private final RoadmapService roadmapService;
	private final ProcessedEventRepository processed;

	public StudentProfileCompletedListener(ObjectMapper m, RoadmapService s, ProcessedEventRepository p) {
		mapper = m;
		roadmapService = s;
		processed = p;
	}

	@KafkaListener(topics = "launch.student-profile-completed.v1", groupId = "roadmap-service-profile-completed")
	@Transactional
	public void consume(String json) throws Exception {
		JsonNode root = mapper.readTree(json);
		UUID eventId = UUID.fromString(root.path("eventId").asText());
		if (processed.existsById(eventId))
			return;
		JsonNode p = root.path("payload");
		long studentId = p.path("studentId").asLong();
		GenerateRoadmapRequest r = new GenerateRoadmapRequest();
		r.setFaculty(text(p, "stream"));
		r.setDegree(text(p, "degree"));
		String specialization = text(p, "specialization");
		r.setBranch(specialization == null || specialization.isBlank() ? text(p, "branch") : specialization);
		r.setTargetRole(text(p, "targetRole"));
		roadmapService.generate(studentId, r);
		ProcessedEvent done = new ProcessedEvent();
		done.setEventId(eventId);
		done.setEventType(root.path("eventType").asText());
		done.setConsumerName("roadmap-service-profile-completed");
		done.setAggregateId(root.path("aggregateId").asText());
		done.setProcessedAt(java.time.Instant.now());
		processed.save(done);
	}

	private String text(JsonNode node, String name) {
		JsonNode v = node.get(name);
		return v == null || v.isNull() ? null : v.asText();
	}
}
