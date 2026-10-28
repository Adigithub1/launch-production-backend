package com.launch.placementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.launch.placementservice.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}