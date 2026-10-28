package com.launch.assessmentservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AssessmentResponse {
	private String id;
	private String title;
	private String roundType;
	private Long durationMinutes;
	private Long passScore;
	private String status;
}