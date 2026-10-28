package com.launch.learningservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "learning_courses")
public class LearningCourse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "student_id")
	private Long studentId;
	@Column(name = "student_roadmap_id")
	private Long studentRoadmapId;
	@Column(name = "roadmap_key")
	private String roadmapKey;
	@Column(name = "node_key")
	private String nodeKey;
	private String title;
	private String category;
	private String difficulty;
	@Column(name = "duration_minutes")
	private Integer durationMinutes;
	@Column(name = "sequence_number")
	private Integer sequenceNumber;
	private Integer progress;
	private String status;
	@Column(name = "source_type")
	private String sourceType;

	public Long getId() {
		return id;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long v) {
		studentId = v;
	}

	public Long getStudentRoadmapId() {
		return studentRoadmapId;
	}

	public void setStudentRoadmapId(Long v) {
		studentRoadmapId = v;
	}

	public String getRoadmapKey() {
		return roadmapKey;
	}

	public void setRoadmapKey(String v) {
		roadmapKey = v;
	}

	public String getNodeKey() {
		return nodeKey;
	}

	public void setNodeKey(String v) {
		nodeKey = v;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String v) {
		title = v;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String v) {
		category = v;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String v) {
		difficulty = v;
	}

	public Integer getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(Integer v) {
		durationMinutes = v;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer v) {
		sequenceNumber = v;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer v) {
		progress = v;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String v) {
		status = v;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String v) {
		sourceType = v;
	}
}
