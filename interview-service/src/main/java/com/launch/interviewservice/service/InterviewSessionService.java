package com.launch.interviewservice.service;

import java.util.List;

import com.launch.interviewservice.dto.request.InterviewSessionRequest;
import com.launch.interviewservice.dto.response.InterviewSessionResponse;

public interface InterviewSessionService {
	InterviewSessionResponse create(InterviewSessionRequest r);

	InterviewSessionResponse get(Long id);

	List<InterviewSessionResponse> list();

	InterviewSessionResponse update(Long id, InterviewSessionRequest r);

	void delete(Long id);
}