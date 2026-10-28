package com.launch.projectservice.dto.response;

import lombok.Builder;

@Builder
public class ProjectResponse {
	private Long id;
	private Long studentId;
	private Long studentRoadmapId;
	private String roadmapKey;
	private String nodeKey;
	private String targetRole;
	private String title;
	private String stream;
	private String level;
	private String instructions;
	private String rubric;
	private String status;
	private String sourceType;
	private Integer sequenceNumber;
	private Integer progress;

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

}
