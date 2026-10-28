package com.launch.notificationservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequest {
	@jakarta.validation.constraints.NotNull
	private Long userId;
	@jakarta.validation.constraints.NotNull
	private String channel;
	@jakarta.validation.constraints.NotNull
	private String title;
	@jakarta.validation.constraints.NotNull
	private String message;
	@jakarta.validation.constraints.NotNull
	private String status;
	@jakarta.validation.constraints.NotNull
	private Boolean readFlag;
}