package com.launch.interviewservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterviewSessionRequest {
	@jakarta.validation.constraints.NotNull
	private Long studentId;
	@jakarta.validation.constraints.NotNull
	private String roundType;
	@jakarta.validation.constraints.NotNull
	private String status;
	@jakarta.validation.constraints.NotNull
	private Integer score;
	@jakarta.validation.constraints.NotNull
	private String weaknesses;
}