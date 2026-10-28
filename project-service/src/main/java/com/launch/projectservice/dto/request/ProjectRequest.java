package com.launch.projectservice.dto.request;

import jakarta.validation.constraints.NotBlank;

public class ProjectRequest {

	@NotBlank
	private String title;

	@NotBlank
	private String stream;

	@NotBlank
	private String level;

	@NotBlank
	private String instructions;

	@NotBlank
	private String rubric;

	@NotBlank
	private String status;

	public ProjectRequest() {
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
}