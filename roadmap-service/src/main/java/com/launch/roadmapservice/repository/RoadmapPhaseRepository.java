package com.launch.roadmapservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.launch.roadmapservice.entity.RoadmapPhase;

public interface RoadmapPhaseRepository extends JpaRepository<RoadmapPhase, Long> {
	List<RoadmapPhase> findByRoadmapIdOrderByPhaseNumber(Long roadmapId);

	void deleteByRoadmapId(Long roadmapId);
}
