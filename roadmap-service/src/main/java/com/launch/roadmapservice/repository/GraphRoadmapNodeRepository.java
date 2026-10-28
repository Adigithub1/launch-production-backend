package com.launch.roadmapservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.launch.roadmapservice.entity.GraphRoadmapNode;

public interface GraphRoadmapNodeRepository extends JpaRepository<GraphRoadmapNode, Long> {
	List<GraphRoadmapNode> findByRoadmapIdOrderByDisplayOrder(Long roadmapId);

	Optional<GraphRoadmapNode> findByRoadmapIdAndNodeKey(Long roadmapId, String nodeKey);

	void deleteByRoadmapId(Long roadmapId);
}
