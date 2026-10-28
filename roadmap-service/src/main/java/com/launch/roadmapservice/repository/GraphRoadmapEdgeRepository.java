package com.launch.roadmapservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.launch.roadmapservice.entity.GraphRoadmapEdge;

public interface GraphRoadmapEdgeRepository extends JpaRepository<GraphRoadmapEdge, Long> {
	List<GraphRoadmapEdge> findByRoadmapId(Long roadmapId);

	void deleteByRoadmapId(Long roadmapId);
}
