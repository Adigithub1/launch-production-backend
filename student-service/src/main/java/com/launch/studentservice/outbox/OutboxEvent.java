package com.launch.studentservice.outbox;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "event_outbox")
public class OutboxEvent {
	@Id
	private UUID id;
	@Column(name = "aggregate_type", nullable = false)
	private String aggregateType;
	@Column(name = "aggregate_id", nullable = false)
	private String aggregateId;
	@Column(name = "event_type", nullable = false)
	private String eventType;
	@Column(nullable = false)
	private String topic;
	@Column(name = "event_key", nullable = false)
	private String eventKey;
	@Column(nullable = false, columnDefinition = "text")
	private String payload;
	@Column(nullable = false)
	private String status;
	@Column(nullable = false)
	private Integer attempts;
	@Column(name = "created_at", nullable = false)
	private Instant createdAt;
	@Column(name = "published_at")
	private Instant publishedAt;
	@Column(name = "last_error")
	private String lastError;

	public OutboxEvent() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID v) {
		id = v;
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

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String v) {
		eventType = v;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String v) {
		topic = v;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String v) {
		eventKey = v;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String v) {
		payload = v;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String v) {
		status = v;
	}

	public Integer getAttempts() {
		return attempts;
	}

	public void setAttempts(Integer v) {
		attempts = v;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant v) {
		createdAt = v;
	}

	public Instant getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Instant v) {
		publishedAt = v;
	}

	public String getLastError() {
		return lastError;
	}

	public void setLastError(String v) {
		lastError = v;
	}
}
