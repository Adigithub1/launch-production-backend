package com.launch.roadmapservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.launch.roadmapservice.entity.LearningResource;

public interface LearningResourceRepository extends JpaRepository<LearningResource, Long> {
	List<LearningResource> findByNodeId(Long nodeId);

	void deleteByNodeId(Long nodeId);
}
