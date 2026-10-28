package com.launch.projectservice.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "projects", uniqueConstraints = @UniqueConstraint(name = "uk_project_student_roadmap_node", columnNames = {
		"student_id", "roadmap_key", "node_key" }))

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "student_id")
	private Long studentId;

	@Column(name = "student_roadmap_id")
	private Long studentRoadmapId;

	@Column(name = "roadmap_key", nullable = false)
	private String roadmapKey;

	@Column(name = "node_key", nullable = false)
	private String nodeKey;

	@Column(name = "target_role")
	private String targetRole;

	private String title;
	private String stream;
	private String level;

	@Column(length = 4000)
	private String instructions;

	@Column(length = 4000)
	private String rubric;

	private String status;

	@Column(name = "source_type")
	private String sourceType;

	@Column(name = "sequence_number")
	private Integer sequenceNumber;

	private Integer progress;
	private Instant createdAt;
	private Instant updatedAt;

	@PrePersist
	void create() {
		createdAt = updatedAt = Instant.now();
		if (progress == null)
			progress = 0;
		if (status == null || status.isBlank())
			status = "NOT_STARTED";
	}

	@PreUpdate
	void update() {
		updatedAt = Instant.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getStudentRoadmapId() {
		return studentRoadmapId;
	}

	public void setStudentRoadmapId(Long studentRoadmapId) {
		this.studentRoadmapId = studentRoadmapId;
	}

	public String getRoadmapKey() {
		return roadmapKey;
	}

	public void setRoadmapKey(String roadmapKey) {
		this.roadmapKey = roadmapKey;
	}

	public String getNodeKey() {
		return nodeKey;
	}

	public void setNodeKey(String nodeKey) {
		this.nodeKey = nodeKey;
	}

	public String getTargetRole() {
		return targetRole;
	}

	public void setTargetRole(String targetRole) {
		this.targetRole = targetRole;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getRubric() {
		return rubric;
	}

	public void setRubric(String rubric) {
		this.rubric = rubric;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

}
