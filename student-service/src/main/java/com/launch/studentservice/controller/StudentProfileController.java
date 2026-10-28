package com.launch.studentservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.launch.studentservice.dto.request.StudentProfileRequest;
import com.launch.studentservice.dto.response.StudentProfileResponse;
import com.launch.studentservice.service.StudentProfileService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class StudentProfileController {

    private final StudentProfileService service;

    @PostMapping
    public ResponseEntity<StudentProfileResponse> create(
            @Valid
            @RequestBody
            StudentProfileRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @GetMapping("/{id}")
    public StudentProfileResponse get(
            @PathVariable Long id) {

        return service.get(id);
    }

    @GetMapping("/user/{userId}")
    public StudentProfileResponse getByUserId(
            @PathVariable Long userId) {

        return service.getByUserId(userId);
    }

    @GetMapping
    public List<StudentProfileResponse> list() {

        return service.list();
    }

    @PutMapping("/{id}")
    public StudentProfileResponse update(
            @PathVariable Long id,
            @Valid
            @RequestBody
            StudentProfileRequest request) {

        return service.update(
                id,
                request
        );
    }

    @PutMapping("/user/{userId}")
    public StudentProfileResponse updateByUserId(
            @PathVariable Long userId,
            @Valid
            @RequestBody
            StudentProfileRequest request) {

        return service.updateByUserId(
                userId,
                request
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id) {

        service.delete(id);
    }
}