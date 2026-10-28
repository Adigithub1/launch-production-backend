package com.launch.notificationservice.service;

import java.util.List;

import com.launch.notificationservice.dto.request.NotificationRequest;
import com.launch.notificationservice.dto.response.NotificationResponse;

public interface NotificationService {
	NotificationResponse create(NotificationRequest r);

	NotificationResponse get(Long id);

	List<NotificationResponse> list();

	NotificationResponse update(Long id, NotificationRequest r);

	void delete(Long id);
}