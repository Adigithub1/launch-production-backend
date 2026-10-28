package com.launch.studentservice.outbox;

import java.time.Instant;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OutboxPublisher {
	private final OutboxEventRepository repository;
	private final KafkaTemplate<String, String> kafka;

	public OutboxPublisher(OutboxEventRepository r, KafkaTemplate<String, String> k) {
		repository = r;
		kafka = k;
	}

	@Scheduled(fixedDelayString = "${launch.outbox.interval-ms:1000}")
	public void publish() {
		for (OutboxEvent e : repository.findTop100ByStatusOrderByCreatedAtAsc("PENDING")) {
			try {
				kafka.send(e.getTopic(), e.getEventKey(), e.getPayload()).get();
				e.setStatus("PUBLISHED");
				e.setPublishedAt(Instant.now());
				e.setLastError(null);
			} catch (Exception ex) {
				e.setAttempts(e.getAttempts() + 1);
				e.setLastError(ex.getMessage());
				if (e.getAttempts() >= 10)
					e.setStatus("FAILED");
			}
			repository.save(e);
		}
	}
}
