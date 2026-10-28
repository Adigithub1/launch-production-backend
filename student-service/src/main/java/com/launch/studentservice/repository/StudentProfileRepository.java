package com.launch.studentservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.launch.studentservice.entity.StudentProfile;

public interface StudentProfileRepository
        extends JpaRepository<StudentProfile, Long> {

    Optional<StudentProfile> findByUserId(Long userId);

    Optional<StudentProfile> findByEmailIgnoreCase(String email);

    boolean existsByUserId(Long userId);
}