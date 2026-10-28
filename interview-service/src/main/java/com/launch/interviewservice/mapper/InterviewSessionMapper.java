package com.launch.interviewservice.mapper;

import org.springframework.stereotype.Component;

import com.launch.interviewservice.dto.request.InterviewSessionRequest;
import com.launch.interviewservice.dto.response.InterviewSessionResponse;
import com.launch.interviewservice.entity.InterviewSession;

@Component
public class InterviewSessionMapper {
	public InterviewSession toEntity(InterviewSessionRequest x) {
		return InterviewSession.builder().studentId(x.getStudentId()).roundType(x.getRoundType()).status(x.getStatus())
				.score(x.getScore()).weaknesses(x.getWeaknesses()).build();
	}

	public void update(InterviewSession e, InterviewSessionRequest x) {
		e.setStudentId(x.getStudentId());
		e.setRoundType(x.getRoundType());
		e.setStatus(x.getStatus());
		e.setScore(x.getScore());
		e.setWeaknesses(x.getWeaknesses());
	}

	public InterviewSessionResponse toResponse(InterviewSession x) {
		return InterviewSessionResponse.builder().id(x.getId()).studentId(x.getStudentId()).roundType(x.getRoundType())
				.status(x.getStatus()).score(x.getScore()).weaknesses(x.getWeaknesses()).build();
	}
}