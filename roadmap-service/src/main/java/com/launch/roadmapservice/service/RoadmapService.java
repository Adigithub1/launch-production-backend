package com.launch.roadmapservice.service;

import java.util.List;

import com.launch.roadmapservice.dto.request.GenerateRoadmapRequest;
import com.launch.roadmapservice.dto.request.RoadmapRequest;
import com.launch.roadmapservice.dto.response.RoadmapResponse;

public interface RoadmapService {
	RoadmapResponse create(RoadmapRequest r);

	RoadmapResponse get(Long id);

	List<RoadmapResponse> list();

	RoadmapResponse update(Long id, RoadmapRequest r);

	void delete(Long id);

	RoadmapResponse getByStudent(Long studentId);

	RoadmapResponse generate(Long studentId, GenerateRoadmapRequest r);

	RoadmapResponse activate(Long id);

}
