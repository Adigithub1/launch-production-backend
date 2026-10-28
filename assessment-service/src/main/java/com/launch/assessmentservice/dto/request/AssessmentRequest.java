package com.launch.assessmentservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssessmentRequest {
	@jakarta.validation.constraints.NotNull
	private String title;
	@jakarta.validation.constraints.NotNull
	private String roundType;
	@jakarta.validation.constraints.NotNull
	private Long durationMinutes;
	@jakarta.validation.constraints.NotNull
	private Long passScore;
	@jakarta.validation.constraints.NotNull
	private String status;
}