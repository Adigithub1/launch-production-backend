package com.launch.learningservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.launch.learningservice.dto.request.CourseRequest;
import com.launch.learningservice.dto.response.CourseResponse;
import com.launch.learningservice.entity.Course;
import com.launch.learningservice.exception.ResourceNotFoundException;
import com.launch.learningservice.mapper.CourseMapper;
import com.launch.learningservice.repository.CourseRepository;
import com.launch.learningservice.service.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repo;
    private final CourseMapper mapper;

    public CourseServiceImpl(
            CourseRepository repo,
            CourseMapper mapper) {

        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public CourseResponse create(CourseRequest request) {

        Course entity =
                mapper.toEntity(request);

        Course saved =
                repo.save(entity);

        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseResponse get(Long id) {

        Course entity =
                repo.findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Course not found: " + id));

        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponse> list() {

        return repo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public CourseResponse update(
            Long id,
            CourseRequest request) {

        Course entity =
                repo.findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Course not found: " + id));

        mapper.update(entity, request);

        Course saved =
                repo.save(entity);

        return mapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {

        Course entity =
                repo.findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Course not found: " + id));

        repo.delete(entity);
    }
}