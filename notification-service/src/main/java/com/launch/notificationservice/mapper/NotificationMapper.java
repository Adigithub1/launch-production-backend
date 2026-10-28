package com.launch.notificationservice.mapper;

import org.springframework.stereotype.Component;

import com.launch.notificationservice.dto.request.NotificationRequest;
import com.launch.notificationservice.dto.response.NotificationResponse;
import com.launch.notificationservice.entity.Notification;

@Component
public class NotificationMapper {
	public Notification toEntity(NotificationRequest x) {
		return Notification.builder().userId(x.getUserId()).channel(x.getChannel()).title(x.getTitle())
				.message(x.getMessage()).status(x.getStatus()).readFlag(x.getReadFlag()).build();
	}

	public void update(Notification e, NotificationRequest x) {
		e.setUserId(x.getUserId());
		e.setChannel(x.getChannel());
		e.setTitle(x.getTitle());
		e.setMessage(x.getMessage());
		e.setStatus(x.getStatus());
		e.setReadFlag(x.getReadFlag());
	}

	public NotificationResponse toResponse(Notification x) {
		return NotificationResponse.builder().id(x.getId()).userId(x.getUserId()).channel(x.getChannel())
				.title(x.getTitle()).message(x.getMessage()).status(x.getStatus()).readFlag(x.getReadFlag()).build();
	}
}