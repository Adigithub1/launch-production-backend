package com.launch.interviewservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.launch.interviewservice.entity.InterviewSession;

@Repository
public interface InterviewSessionRepository extends JpaRepository<InterviewSession, Long> {
}