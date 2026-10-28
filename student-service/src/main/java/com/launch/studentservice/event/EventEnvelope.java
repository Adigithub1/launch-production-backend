package com.launch.studentservice.event;

import java.time.Instant;
import java.util.UUID;

public class EventEnvelope<T> {
	private UUID eventId;
	private String eventType;
	private Integer eventVersion;
	private String aggregateType;
	private String aggregateId;
	private String correlationId;
	private Instant occurredAt;
	private String producer;
	private T payload;

	public EventEnvelope() {
	}

	public static <T> EventEnvelope<T> create(String type, String aggregateType, String aggregateId, String producer,
			T payload) {
		EventEnvelope<T> e = new EventEnvelope<>();
		e.eventId = UUID.randomUUID();
		e.eventType = type;
		e.eventVersion = 1;
		e.aggregateType = aggregateType;
		e.aggregateId = aggregateId;
		e.correlationId = UUID.randomUUID().toString();
		e.occurredAt = Instant.now();
		e.producer = producer;
		e.payload = payload;
		return e;
	}

	public UUID getEventId() {
		return eventId;
	}

	public void setEventId(UUID v) {
		eventId = v;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String v) {
		eventType = v;
	}

	public Integer getEventVersion() {
		return eventVersion;
	}

	public void setEventVersion(Integer v) {
		eventVersion = v;
	}

	public String getAggregateType() {
		return aggregateType;
	}

	public void setAggregateType(String v) {
		aggregateType = v;
	}

	public String getAggregateId() {
		return aggregateId;
	}

	public void setAggregateId(String v) {
		aggregateId = v;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String v) {
		correlationId = v;
	}

	public Instant getOccurredAt() {
		return occurredAt;
	}

	public void setOccurredAt(Instant v) {
		occurredAt = v;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String v) {
		producer = v;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T v) {
		payload = v;
	}
}
