package com.launch.learningservice.mapper;

import org.springframework.stereotype.Component;

import com.launch.learningservice.dto.request.CourseRequest;
import com.launch.learningservice.dto.response.CourseResponse;
import com.launch.learningservice.entity.Course;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequest request) {
        Course course = new Course();
        copyRequest(course, request, false);
        return course;
    }

    public void update(Course course, CourseRequest request) {
        copyRequest(course, request, true);
    }

    private void copyRequest(Course course, CourseRequest request, boolean preserveNullRoadmapFields) {
        course.setTitle(request.getTitle());
        course.setCategory(request.getCategory());
        course.setStream(request.getStream());
        course.setDescription(request.getDescription());
        course.setDifficulty(request.getDifficulty());
        course.setStatus(request.getStatus());

        if (!preserveNullRoadmapFields || request.getRoadmapKey() != null) {
            course.setRoadmapKey(request.getRoadmapKey());
        }
        if (!preserveNullRoadmapFields || request.getNodeKey() != null) {
            course.setNodeKey(request.getNodeKey());
        }
        if (!preserveNullRoadmapFields || request.getStudentRoadmapId() != null) {
            course.setStudentRoadmapId(request.getStudentRoadmapId());
        }
        if (!preserveNullRoadmapFields || request.getSourceType() != null) {
            course.setSourceType(request.getSourceType());
        }
        if (!preserveNullRoadmapFields || request.getDurationMinutes() != null) {
            course.setDurationMinutes(request.getDurationMinutes());
        }
        if (!preserveNullRoadmapFields || request.getSequenceNumber() != null) {
            course.setSequenceNumber(request.getSequenceNumber());
        }
    }

    public CourseResponse toResponse(Course course) {
        CourseResponse response = new CourseResponse();
        response.setId(course.getId());
        response.setTitle(course.getTitle());
        response.setCategory(course.getCategory());
        response.setStream(course.getStream());
        response.setDescription(course.getDescription());
        response.setDifficulty(course.getDifficulty());
        response.setStatus(course.getStatus());
        response.setRoadmapKey(course.getRoadmapKey());
        response.setNodeKey(course.getNodeKey());
        response.setStudentRoadmapId(course.getStudentRoadmapId());
        response.setSourceType(course.getSourceType());
        response.setDurationMinutes(course.getDurationMinutes());
        response.setSequenceNumber(course.getSequenceNumber());
        return response;
    }
}
