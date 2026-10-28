package com.launch.billingservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.launch.billingservice.dto.request.SubscriptionRequest;
import com.launch.billingservice.dto.response.SubscriptionResponse;
import com.launch.billingservice.exception.ResourceNotFoundException;
import com.launch.billingservice.mapper.SubscriptionMapper;
import com.launch.billingservice.repository.SubscriptionRepository;
import com.launch.billingservice.service.SubscriptionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {
	private final SubscriptionRepository repo;
	private final SubscriptionMapper mapper;

	public SubscriptionResponse create(SubscriptionRequest r) {
		return mapper.toResponse(repo.save(mapper.toEntity(r)));
	}

	@Transactional(readOnly = true)
	public SubscriptionResponse get(Long id) {
		return mapper.toResponse(
				repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subscription not found")));
	}

	@Transactional(readOnly = true)
	public List<SubscriptionResponse> list() {
		return repo.findAll().stream().map(mapper::toResponse).toList();
	}

	public SubscriptionResponse update(Long id, SubscriptionRequest r) {
		var e = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subscription not found"));
		mapper.update(e, r);
		return mapper.toResponse(repo.save(e));
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}