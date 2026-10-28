package com.launch.billingservice.service;

import java.util.List;

import com.launch.billingservice.dto.request.SubscriptionRequest;
import com.launch.billingservice.dto.response.SubscriptionResponse;

public interface SubscriptionService {
	SubscriptionResponse create(SubscriptionRequest r);

	SubscriptionResponse get(Long id);

	List<SubscriptionResponse> list();

	SubscriptionResponse update(Long id, SubscriptionRequest r);

	void delete(Long id);
}