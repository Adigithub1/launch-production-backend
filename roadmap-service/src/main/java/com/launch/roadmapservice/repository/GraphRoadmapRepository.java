package com.launch.roadmapservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.launch.roadmapservice.entity.GraphRoadmap;

public interface GraphRoadmapRepository extends JpaRepository<GraphRoadmap, Long> {

	Optional<GraphRoadmap> findByStudentIdAndRoadmapKey(Long studentId, String roadmapKey);
}