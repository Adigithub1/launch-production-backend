package com.launch.learningservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.launch.learningservice.entity.LearningCourse;

public interface LearningCourseRepository
        extends JpaRepository<LearningCourse, Long> {

    List<LearningCourse>
            findByStudentIdAndRoadmapKeyOrderBySequenceNumberAsc(
                    Long studentId,
                    String roadmapKey
            );

    Optional<LearningCourse>
            findByStudentIdAndRoadmapKeyAndNodeKey(
                    Long studentId,
                    String roadmapKey,
                    String nodeKey
            );
}