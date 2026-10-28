package com.launch.studentservice.mapper;

import org.springframework.stereotype.Component;

import com.launch.studentservice.dto.request.StudentProfileRequest;
import com.launch.studentservice.dto.response.StudentProfileResponse;
import com.launch.studentservice.entity.StudentProfile;

@Component
public class StudentProfileMapper {

    public StudentProfile toEntity(
            StudentProfileRequest request) {

        return StudentProfile.builder()
                .userId(request.getUserId())
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .stream(request.getStream())
                .branch(request.getBranch())
                .specialization(
                        request.getSpecialization()
                )
                .targetRole(request.getTargetRole())
                .designation(request.getDesignation())
                .college(request.getCollege())
                .graduationYear(
                        request.getGraduationYear()
                )
                .skills(request.getSkills())
                .interests(request.getInterests())
                .resumeKey(request.getResumeKey())
                .build();
    }

    public void update(
            StudentProfile entity,
            StudentProfileRequest request) {

        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setStream(request.getStream());
        entity.setBranch(request.getBranch());

        entity.setSpecialization(
                request.getSpecialization()
        );

        entity.setTargetRole(
                request.getTargetRole()
        );

        entity.setDesignation(
                request.getDesignation()
        );

        entity.setCollege(request.getCollege());

        entity.setGraduationYear(
                request.getGraduationYear()
        );

        entity.setSkills(request.getSkills());

        entity.setInterests(
                request.getInterests()
        );

        entity.setResumeKey(
                request.getResumeKey()
        );
    }

    public StudentProfileResponse toResponse(
            StudentProfile entity) {

        return StudentProfileResponse.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .stream(entity.getStream())
                .branch(entity.getBranch())
                .specialization(
                        entity.getSpecialization()
                )
                .targetRole(entity.getTargetRole())
                .designation(entity.getDesignation())
                .college(entity.getCollege())
                .graduationYear(
                        entity.getGraduationYear()
                )
                .skills(entity.getSkills())
                .interests(entity.getInterests())
                .resumeKey(entity.getResumeKey())
                .profileCompletion(
                        calculateProfileCompletion(entity)
                )
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private int calculateProfileCompletion(
            StudentProfile p) {

        int total = 11;
        int completed = 0;

        if (hasValue(p.getName())) completed++;
        if (hasValue(p.getEmail())) completed++;
        if (hasValue(p.getPhone())) completed++;
        if (hasValue(p.getStream())) completed++;
        if (hasValue(p.getCollege())) completed++;
        if (hasValue(p.getGraduationYear())) completed++;
        if (hasValue(p.getTargetRole())) completed++;
        if (hasValue(p.getDesignation())) completed++;
        if (hasValue(p.getSkills())) completed++;
        if (hasValue(p.getInterests())) completed++;
        if (hasValue(p.getResumeKey())) completed++;

        return Math.round(
                completed * 100f / total
        );
    }

    private boolean hasValue(String value) {
        return value != null &&
                !value.trim().isEmpty();
    }
}