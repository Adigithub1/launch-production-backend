package com.launch.studentservice.outbox;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OutboxService {
	private final OutboxEventRepository repository;
	private final ObjectMapper objectMapper;

	public OutboxService(OutboxEventRepository r, ObjectMapper o) {
		repository = r;
		objectMapper = o;
	}

	public void enqueue(String topic, String key, String aggregateType, String aggregateId, String eventType,
			Object event) {
		OutboxEvent x = new OutboxEvent();
		x.setId(UUID.randomUUID());
		x.setTopic(topic);
		x.setEventKey(key);
		x.setAggregateType(aggregateType);
		x.setAggregateId(aggregateId);
		x.setEventType(eventType);
		x.setStatus("PENDING");
		x.setAttempts(0);
		x.setCreatedAt(Instant.now());
		try {
			x.setPayload(objectMapper.writeValueAsString(event));
		} catch (Exception e) {
			throw new IllegalStateException("Cannot serialize outbox event", e);
		}
		repository.save(x);
	}
}
