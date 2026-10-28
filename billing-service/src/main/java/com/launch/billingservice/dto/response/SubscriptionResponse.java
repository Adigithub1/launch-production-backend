package com.launch.billingservice.dto.response;

import lombok.Builder;

@Builder
public class SubscriptionResponse {

	private Long id;

	private Long userId;

	private String plan;

	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SubscriptionResponse [id=" + id + ", userId=" + userId + ", plan=" + plan + ", status=" + status + "]";
	}

}