package com.launch.projectservice.service;

import java.util.List;

import com.launch.projectservice.dto.request.ProjectRequest;
import com.launch.projectservice.dto.response.ProjectResponse;

public interface ProjectService {

	ProjectResponse create(ProjectRequest request);

	ProjectResponse get(Long id);

	List<ProjectResponse> list();

	ProjectResponse update(Long id, ProjectRequest request);

	void delete(Long id);
}