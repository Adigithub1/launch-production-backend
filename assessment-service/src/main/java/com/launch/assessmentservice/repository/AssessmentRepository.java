package com.launch.assessmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.launch.assessmentservice.entity.Assessment;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
}