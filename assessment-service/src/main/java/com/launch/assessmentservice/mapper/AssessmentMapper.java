package com.launch.assessmentservice.mapper;

import org.springframework.stereotype.Component;

import com.launch.assessmentservice.dto.request.AssessmentRequest;
import com.launch.assessmentservice.dto.response.AssessmentResponse;
import com.launch.assessmentservice.entity.Assessment;

@Component
public class AssessmentMapper {
	public Assessment toEntity(AssessmentRequest x) {
		return Assessment.builder().title(x.getTitle()).roundType(x.getRoundType())
				.durationMinutes(x.getDurationMinutes()).passScore(x.getPassScore()).status(x.getStatus()).build();
	}

	public void update(Assessment e, AssessmentRequest x) {
		e.setTitle(x.getTitle());
		e.setRoundType(x.getRoundType());
		e.setDurationMinutes(x.getDurationMinutes());
		e.setPassScore(x.getPassScore());
		e.setStatus(x.getStatus());
	}

	public AssessmentResponse toResponse(Assessment x) {
		return AssessmentResponse.builder().id(x.getId()).title(x.getTitle()).roundType(x.getRoundType())
				.durationMinutes(x.getDurationMinutes()).passScore(x.getPassScore()).status(x.getStatus()).build();
	}
}