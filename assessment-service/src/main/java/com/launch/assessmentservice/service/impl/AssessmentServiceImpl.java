package com.launch.assessmentservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.launch.assessmentservice.dto.request.AssessmentRequest;
import com.launch.assessmentservice.dto.response.AssessmentResponse;
import com.launch.assessmentservice.exception.ResourceNotFoundException;
import com.launch.assessmentservice.mapper.AssessmentMapper;
import com.launch.assessmentservice.repository.AssessmentRepository;
import com.launch.assessmentservice.service.AssessmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AssessmentServiceImpl implements AssessmentService {
	private final AssessmentRepository repo;
	private final AssessmentMapper mapper;

	public AssessmentResponse create(AssessmentRequest r) {
		return mapper.toResponse(repo.save(mapper.toEntity(r)));
	}

	@Transactional(readOnly = true)
	public AssessmentResponse get(Long id) {
		return mapper
				.toResponse(repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Assessment not found")));
	}

	@Transactional(readOnly = true)
	public List<AssessmentResponse> list() {
		return repo.findAll().stream().map(mapper::toResponse).toList();
	}

	public AssessmentResponse update(Long id, AssessmentRequest r) {
		var e = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Assessment not found"));
		mapper.update(e, r);
		return mapper.toResponse(repo.save(e));
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}