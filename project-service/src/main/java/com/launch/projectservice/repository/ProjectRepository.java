package com.launch.projectservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.launch.projectservice.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	List<Project> findByStudentIdAndRoadmapKeyOrderBySequenceNumberAsc(Long studentId, String roadmapKey);

	Optional<Project> findByStudentIdAndRoadmapKeyAndNodeKey(Long studentId, String roadmapKey, String nodeKey);
}
