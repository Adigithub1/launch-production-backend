package com.launch.roadmapservice.dto.response;

import java.util.List;

public class RoadmapResponse {

	private Long id;
	private Long studentId;
	private Long version;

	private String targetRole;
	private String label;
	private String status;

	private Integer readiness;
	private Integer overallProgress;

	private List<String> currentSkills;
	private List<String> missingSkills;

	private List<PhaseResponse> phases;
	private List<String> projects;

	// Required by RoadmapMapper: new RoadmapResponse()
	public RoadmapResponse() {
	}

	public RoadmapResponse(Long id, Long studentId, Long version, String targetRole, String label, String status,
			Integer readiness, Integer overallProgress, List<String> currentSkills, List<String> missingSkills,
			List<PhaseResponse> phases, List<String> projects) {

		this.id = id;
		this.studentId = studentId;
		this.version = version;
		this.targetRole = targetRole;
		this.label = label;
		this.status = status;
		this.readiness = readiness;
		this.overallProgress = overallProgress;
		this.currentSkills = currentSkills;
		this.missingSkills = missingSkills;
		this.phases = phases;
		this.projects = projects;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
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

	public Integer getReadiness() {
		return readiness;
	}

	public void setReadiness(Integer readiness) {
		this.readiness = readiness;
	}

	public Integer getOverallProgress() {
		return overallProgress;
	}

	public void setOverallProgress(Integer overallProgress) {
		this.overallProgress = overallProgress;
	}

	public List<String> getCurrentSkills() {
		return currentSkills;
	}

	public void setCurrentSkills(List<String> currentSkills) {
		this.currentSkills = currentSkills;
	}

	public List<String> getMissingSkills() {
		return missingSkills;
	}

	public void setMissingSkills(List<String> missingSkills) {
		this.missingSkills = missingSkills;
	}

	public List<PhaseResponse> getPhases() {
		return phases;
	}

	public void setPhases(List<PhaseResponse> phases) {
		this.phases = phases;
	}

	public List<String> getProjects() {
		return projects;
	}

	public void setProjects(List<String> projects) {
		this.projects = projects;
	}
}