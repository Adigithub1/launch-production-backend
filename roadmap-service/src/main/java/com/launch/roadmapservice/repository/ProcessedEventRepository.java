package com.launch.roadmapservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.launch.roadmapservice.event.ProcessedEvent;

public interface ProcessedEventRepository extends JpaRepository<ProcessedEvent, UUID> {
}
