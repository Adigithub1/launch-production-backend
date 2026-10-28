package com.launch.roadmapservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.launch.roadmapservice.entity.Roadmap;

public interface RoadmapRepository extends JpaRepository<Roadmap, Long> {
	Optional<Roadmap> findByStudentId(Long studentId);
}
