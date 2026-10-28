package com.launch.roadmapservice.dto.request;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public class GenerateRoadmapRequest {
	@NotBlank
	private String faculty;
	private String roadmapKey;
	private String degree;
	private String branch;
	private String specialization;
	@NotBlank
	private String targetRole;
	@Builder.Default
	private List<String> currentSkills = new ArrayList<>();

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getTargetRole() {
		return targetRole;
	}

	public void setTargetRole(String targetRole) {
		this.targetRole = targetRole;
	}

	public List<String> getCurrentSkills() {
		return currentSkills;
	}

	public void setCurrentSkills(List<String> currentSkills) {
		this.currentSkills = currentSkills;
	}

	public String getRoadmapKey() {
		return roadmapKey;
	}

	public void setRoadmapKey(String roadmapKey) {
		this.roadmapKey = roadmapKey;
	}

	@Override
	public String toString() {
		return "GenerateRoadmapRequest [faculty=" + faculty + ", roadmapKey=" + roadmapKey + ", degree=" + degree
				+ ", branch=" + branch + ", specialization=" + specialization + ", targetRole=" + targetRole
				+ ", currentSkills=" + currentSkills + "]";
	}

}
