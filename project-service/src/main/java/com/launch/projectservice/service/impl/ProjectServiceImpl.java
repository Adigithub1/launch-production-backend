package com.launch.projectservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.launch.projectservice.dto.request.ProjectRequest;
import com.launch.projectservice.dto.response.ProjectResponse;
import com.launch.projectservice.exception.ResourceNotFoundException;
import com.launch.projectservice.mapper.ProjectMapper;
import com.launch.projectservice.repository.ProjectRepository;
import com.launch.projectservice.service.ProjectService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository repo;
	private final ProjectMapper mapper;

	public ProjectServiceImpl(ProjectRepository repo, ProjectMapper mapper) {

		this.repo = repo;
		this.mapper = mapper;
	}

	@Override
	public ProjectResponse create(ProjectRequest request) {

		return mapper.toResponse(repo.save(mapper.toEntity(request)));
	}

	@Override
	@Transactional(readOnly = true)
	public ProjectResponse get(Long id) {

		return repo.findById(id).map(mapper::toResponse)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found: " + id));
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProjectResponse> list() {

		return repo.findAll().stream().map(mapper::toResponse).toList();
	}

	@Override
	public ProjectResponse update(Long id, ProjectRequest request) {

		var entity = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found: " + id));

		mapper.update(entity, request);

		return mapper.toResponse(repo.save(entity));
	}

	@Override
	public void delete(Long id) {

		var entity = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found: " + id));

		repo.delete(entity);
	}
}