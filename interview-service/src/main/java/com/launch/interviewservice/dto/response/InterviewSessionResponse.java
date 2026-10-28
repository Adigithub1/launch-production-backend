package com.launch.interviewservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InterviewSessionResponse {
	private String id;
	private Long studentId;
	private String roundType;
	private String status;
	private Integer score;
	private String weaknesses;
}