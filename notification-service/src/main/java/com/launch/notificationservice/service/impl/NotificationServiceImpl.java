package com.launch.notificationservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.launch.notificationservice.dto.request.NotificationRequest;
import com.launch.notificationservice.dto.response.NotificationResponse;
import com.launch.notificationservice.exception.ResourceNotFoundException;
import com.launch.notificationservice.mapper.NotificationMapper;
import com.launch.notificationservice.repository.NotificationRepository;
import com.launch.notificationservice.service.NotificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationServiceImpl implements NotificationService {
	private final NotificationRepository repo;
	private final NotificationMapper mapper;

	public NotificationResponse create(NotificationRequest r) {
		return mapper.toResponse(repo.save(mapper.toEntity(r)));
	}

	@Transactional(readOnly = true)
	public NotificationResponse get(Long id) {
		return mapper.toResponse(
				repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Notification not found")));
	}

	@Transactional(readOnly = true)
	public List<NotificationResponse> list() {
		return repo.findAll().stream().map(mapper::toResponse).toList();
	}

	public NotificationResponse update(Long id, NotificationRequest r) {
		var e = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
		mapper.update(e, r);
		return mapper.toResponse(repo.save(e));
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}