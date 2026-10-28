package com.launch.placementservice.entity;

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
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String company;
	private String role;
	private String location;
	private String mode;
	private String packageRange;
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