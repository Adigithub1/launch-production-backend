package com.launch.interviewservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.launch.interviewservice.dto.request.InterviewSessionRequest;
import com.launch.interviewservice.dto.response.InterviewSessionResponse;
import com.launch.interviewservice.exception.ResourceNotFoundException;
import com.launch.interviewservice.mapper.InterviewSessionMapper;
import com.launch.interviewservice.repository.InterviewSessionRepository;
import com.launch.interviewservice.service.InterviewSessionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class InterviewSessionServiceImpl implements InterviewSessionService {
	private final InterviewSessionRepository repo;
	private final InterviewSessionMapper mapper;

	public InterviewSessionResponse create(InterviewSessionRequest r) {
		return mapper.toResponse(repo.save(mapper.toEntity(r)));
	}

	@Transactional(readOnly = true)
	public InterviewSessionResponse get(Long id) {
		return mapper.toResponse(
				repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("InterviewSession not found")));
	}

	@Transactional(readOnly = true)
	public List<InterviewSessionResponse> list() {
		return repo.findAll().stream().map(mapper::toResponse).toList();
	}

	public InterviewSessionResponse update(Long id, InterviewSessionRequest r) {
		var e = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("InterviewSession not found"));
		mapper.update(e, r);
		return mapper.toResponse(repo.save(e));
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}