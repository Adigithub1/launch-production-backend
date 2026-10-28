package com.launch.studentservice.service;

import java.util.List;

import com.launch.studentservice.dto.request.StudentProfileRequest;
import com.launch.studentservice.dto.response.StudentProfileResponse;

public interface StudentProfileService {

    StudentProfileResponse create(
            StudentProfileRequest request
    );

    StudentProfileResponse get(Long id);

    StudentProfileResponse getByUserId(
            Long userId
    );

    List<StudentProfileResponse> list();

    StudentProfileResponse update(
            Long id,
            StudentProfileRequest request
    );

    StudentProfileResponse updateByUserId(
            Long userId,
            StudentProfileRequest request
    );

    void delete(Long id);
}