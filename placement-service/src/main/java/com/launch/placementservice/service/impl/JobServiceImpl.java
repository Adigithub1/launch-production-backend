package com.launch.placementservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.launch.placementservice.dto.request.JobRequest;
import com.launch.placementservice.dto.response.JobResponse;
import com.launch.placementservice.exception.ResourceNotFoundException;
import com.launch.placementservice.mapper.JobMapper;
import com.launch.placementservice.repository.JobRepository;
import com.launch.placementservice.service.JobService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class JobServiceImpl implements JobService {
	private final JobRepository repo;
	private final JobMapper mapper;

	public JobResponse create(JobRequest r) {
		return mapper.toResponse(repo.save(mapper.toEntity(r)));
	}

	@Transactional(readOnly = true)
	public JobResponse get(Long id) {
		return mapper.toResponse(repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Job not found")));
	}

	@Transactional(readOnly = true)
	public List<JobResponse> list() {
		return repo.findAll().stream().map(mapper::toResponse).toList();
	}

	public JobResponse update(Long id, JobRequest r) {
		var e = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Job not found"));
		mapper.update(e, r);
		return mapper.toResponse(repo.save(e));
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}