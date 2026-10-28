package com.launch.learningservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.launch.learningservice.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}