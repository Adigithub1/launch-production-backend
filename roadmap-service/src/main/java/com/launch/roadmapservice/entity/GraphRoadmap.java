package com.launch.roadmapservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "graph_roadmaps", uniqueConstraints = {
		@UniqueConstraint(name = "uk_graph_roadmap_student_key", columnNames = { "student_id", "roadmap_key" }) })
public class GraphRoadmap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "student_id", nullable = false)
	private Long studentId;

	@Column(name = "roadmap_key", nullable = false)
	private String roadmapKey;

	@Column(name = "target_role", nullable = false)
	private String targetRole;

	@Column(nullable = false)
	private String label;

	@Column(nullable = false)
	private String status = "ACTIVE";

	@Column(name = "overall_progress", nullable = false)
	private Integer overallProgress = 0;

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

	public String getRoadmapKey() {
		return roadmapKey;
	}

	public void setRoadmapKey(String roadmapKey) {
		this.roadmapKey = roadmapKey;
	}

	public String getTargetRole() {
		return targetRole;
	}

	public void setTargetRole(String targetRole) {
		this.targetRole = targetRole;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getOverallProgress() {
		return overallProgress;
	}

	public void setOverallProgress(Integer overallProgress) {
		this.overallProgress = overallProgress;
	}
}