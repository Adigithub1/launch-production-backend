package com.launch.placementservice.service;

import java.util.List;

import com.launch.placementservice.dto.request.JobRequest;
import com.launch.placementservice.dto.response.JobResponse;

public interface JobService {
	JobResponse create(JobRequest r);

	JobResponse get(Long id);

	List<JobResponse> list();

	JobResponse update(Long id, JobRequest r);

	void delete(Long id);
}