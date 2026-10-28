package com.launch.learningservice.service;

import java.util.List;

import com.launch.learningservice.dto.request.CourseRequest;
import com.launch.learningservice.dto.response.CourseResponse;

public interface CourseService {
	CourseResponse create(CourseRequest r);

	CourseResponse get(Long id);

	List<CourseResponse> list();

	CourseResponse update(Long id, CourseRequest r);

	void delete(Long id);
}