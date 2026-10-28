package com.launch.roadmapservice.event;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "processed_events")
public class ProcessedEvent {
	@Id
	@Column(name = "event_id")
	private UUID eventId;
	@Column(name = "event_type", nullable = false)
	private String eventType;
	@Column(name = "consumer_name", nullable = false)
	private String consumerName;
	@Column(name = "aggregate_id")
	private String aggregateId;
	@Column(name = "processed_at", nullable = false)
	private Instant processedAt;

	public ProcessedEvent() {
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

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String v) {
		consumerName = v;
	}

	public String getAggregateId() {
		return aggregateId;
	}

	public void setAggregateId(String v) {
		aggregateId = v;
	}

	public Instant getProcessedAt() {
		return processedAt;
	}

	public void setProcessedAt(Instant v) {
		processedAt = v;
	}
}
