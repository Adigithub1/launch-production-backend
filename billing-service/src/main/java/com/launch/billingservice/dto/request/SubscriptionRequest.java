package com.launch.billingservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionRequest {
	@jakarta.validation.constraints.NotNull
	private Long userId;
	@jakarta.validation.constraints.NotNull
	private String plan;
	@jakarta.validation.constraints.NotNull
	private String status;
}