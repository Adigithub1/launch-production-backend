package com.launch.assessmentservice.service;

import java.util.List;

import com.launch.assessmentservice.dto.request.AssessmentRequest;
import com.launch.assessmentservice.dto.response.AssessmentResponse;

public interface AssessmentService {
	AssessmentResponse create(AssessmentRequest r);

	AssessmentResponse get(Long id);

	List<AssessmentResponse> list();

	AssessmentResponse update(Long id, AssessmentRequest r);

	void delete(Long id);
}