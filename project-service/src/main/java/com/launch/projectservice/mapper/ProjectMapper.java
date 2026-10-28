package com.launch.projectservice.mapper;

import org.springframework.stereotype.Component;

import com.launch.projectservice.dto.request.ProjectRequest;
import com.launch.projectservice.dto.response.ProjectResponse;
import com.launch.projectservice.entity.Project;

@Component
public class ProjectMapper {

	public Project toEntity(ProjectRequest request) {

		Project project = new Project();

		project.setTitle(request.getTitle());
		project.setStream(request.getStream());
		project.setLevel(request.getLevel());
		project.setInstructions(request.getInstructions());
		project.setRubric(request.getRubric());
		project.setStatus(request.getStatus());

		/*
		 * These are generic CRUD projects. Roadmap-specific values are populated by
		 * ProjectSyncService.
		 */
		project.setProgress(0);

		return project;
	}

	public void update(Project project, ProjectRequest request) {

		project.setTitle(request.getTitle());
		project.setStream(request.getStream());
		project.setLevel(request.getLevel());
		project.setInstructions(request.getInstructions());
		project.setRubric(request.getRubric());
		project.setStatus(request.getStatus());
	}

	public ProjectResponse toResponse(Project project) {

		return ProjectResponse.builder()

				.id(project.getId())

				.studentId(project.getStudentId())

				.studentRoadmapId(project.getStudentRoadmapId())

				.roadmapKey(project.getRoadmapKey())

				.nodeKey(project.getNodeKey())

				.targetRole(project.getTargetRole())

				.title(project.getTitle())

				.stream(project.getStream())

				.level(project.getLevel())

				.instructions(project.getInstructions())

				.rubric(project.getRubric())

				.status(project.getStatus())

				.sourceType(project.getSourceType())

				.sequenceNumber(project.getSequenceNumber())

				.progress(project.getProgress())

				.build();
	}
}