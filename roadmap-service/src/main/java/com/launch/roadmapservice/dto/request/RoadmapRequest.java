package com.launch.roadmapservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RoadmapRequest {
	@NotNull
	private Long studentId;
	@NotNull
	private Long version;
	@NotBlank
	private String targetRole;
	@NotBlank
	private String label;
	@NotBlank
	private String status;

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

	@Override
	public String toString() {
		return "RoadmapRequest [studentId=" + studentId + ", version=" + version + ", targetRole=" + targetRole
				+ ", label=" + label + ", status=" + status + "]";
	}

}
