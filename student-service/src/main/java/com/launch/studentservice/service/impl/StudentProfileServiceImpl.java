package com.launch.studentservice.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.launch.studentservice.dto.request.StudentProfileRequest;
import com.launch.studentservice.dto.response.StudentProfileResponse;
import com.launch.studentservice.entity.StudentProfile;
import com.launch.studentservice.event.EventEnvelope;
import com.launch.studentservice.event.StudentProfileCompletedEvent;
import com.launch.studentservice.mapper.StudentProfileMapper;
import com.launch.studentservice.outbox.OutboxService;
import com.launch.studentservice.repository.StudentProfileRepository;
import com.launch.studentservice.service.StudentProfileService;

@Service
@Transactional
public class StudentProfileServiceImpl implements StudentProfileService {
	private final StudentProfileRepository repository;
	private final StudentProfileMapper mapper;
	private final OutboxService outboxService;

	public StudentProfileServiceImpl(StudentProfileRepository r, StudentProfileMapper m, OutboxService o) {
		repository = r;
		mapper = m;
		outboxService = o;
	}

	@Override
	public StudentProfileResponse create(StudentProfileRequest request) {
		if (repository.existsByUserId(request.getUserId()))
			throw new IllegalStateException("Student profile already exists for userId: " + request.getUserId());
		StudentProfile entity = repository.save(mapper.toEntity(request));
		publishIfReady(entity);
		return mapper.toResponse(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public StudentProfileResponse get(Long id) {
		return repository.findById(id).map(mapper::toResponse).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student profile not found: " + id));
	}

	@Override
	@Transactional(readOnly = true)
	public StudentProfileResponse getByUserId(Long userId) {
		return repository.findByUserId(userId).map(mapper::toResponse)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Student profile not found for userId: " + userId));
	}

	@Override
	@Transactional(readOnly = true)
	public List<StudentProfileResponse> list() {
		return repository.findAll().stream().map(mapper::toResponse).toList();
	}

	@Override
	public StudentProfileResponse update(Long id, StudentProfileRequest request) {
		StudentProfile e = repository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student profile not found: " + id));
		mapper.update(e, request);
		e = repository.save(e);
		publishIfReady(e);
		return mapper.toResponse(e);
	}

	@Override
	public StudentProfileResponse updateByUserId(Long userId, StudentProfileRequest request) {
		StudentProfile e = repository.findByUserId(userId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Student profile not found for userId: " + userId));
		mapper.update(e, request);
		e = repository.save(e);
		publishIfReady(e);
		return mapper.toResponse(e);
	}

	@Override
	public void delete(Long id) {
		StudentProfile e = repository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student profile not found: " + id));
		repository.delete(e);
	}

	private List<String> toList(String value) {

	    if (value == null || value.isBlank()) {
	        return List.of();
	    }

	    return java.util.Arrays.stream(value.split(","))
	            .map(String::trim)
	            .filter(item -> !item.isBlank())
	            .distinct()
	            .toList();
	}
	private void publishIfReady(StudentProfile e) {
		if (e.getId() == null || e.getTargetRole() == null || e.getTargetRole().isBlank())
			return;
		StudentProfileCompletedEvent p = new StudentProfileCompletedEvent();
		p.setStudentId(e.getId());
		p.setUserId(e.getUserId());
		p.setStream(e.getStream());
		p.setDegree(null);
		p.setBranch(e.getBranch());
		p.setSpecialization(e.getSpecialization());
		p.setTargetRole(e.getTargetRole());
		p.setGraduationYear(e.getGraduationYear());
		p.setCurrentSkills(toList(e.getSkills()));
		p.setInterests(toList(e.getInterests()));
		EventEnvelope<StudentProfileCompletedEvent> event = EventEnvelope.create("STUDENT_PROFILE_COMPLETED",
				"STUDENT_PROFILE", e.getId().toString(), "student-service", p);
		outboxService.enqueue("launch.student-profile-completed.v1", e.getId().toString(), "STUDENT_PROFILE",
				e.getId().toString(), event.getEventType(), event);
	}
}
