package com.launch.notificationservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NotificationResponse {
	private String id;
	private Long userId;
	private String channel;
	private String title;
	private String message;
	private String status;
	private Boolean readFlag;
}