package com.launch.assessmentservice.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "assessments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assessment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String roundType;
	private Long durationMinutes;
	private Long passScore;
	private String status;
	private Instant createdAt;
	private Instant updatedAt;

	@PrePersist
	void create() {
		createdAt = updatedAt = Instant.now();
	}

	@PreUpdate
	void update() {
		updatedAt = Instant.now();
	}
}