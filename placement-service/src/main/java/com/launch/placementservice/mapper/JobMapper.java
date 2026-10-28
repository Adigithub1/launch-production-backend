package com.launch.placementservice.mapper;

import org.springframework.stereotype.Component;

import com.launch.placementservice.dto.request.JobRequest;
import com.launch.placementservice.dto.response.JobResponse;
import com.launch.placementservice.entity.Job;

@Component
public class JobMapper {
	public Job toEntity(JobRequest x) {
		return Job.builder().company(x.getCompany()).role(x.getRole()).location(x.getLocation()).mode(x.getMode())
				.packageRange(x.getPackageRange()).status(x.getStatus()).build();
	}

	public void update(Job e, JobRequest x) {
		e.setCompany(x.getCompany());
		e.setRole(x.getRole());
		e.setLocation(x.getLocation());
		e.setMode(x.getMode());
		e.setPackageRange(x.getPackageRange());
		e.setStatus(x.getStatus());
	}

	public JobResponse toResponse(Job x) {
		return JobResponse.builder().id(x.getId()).company(x.getCompany()).role(x.getRole()).location(x.getLocation())
				.mode(x.getMode()).packageRange(x.getPackageRange()).status(x.getStatus()).build();
	}
}