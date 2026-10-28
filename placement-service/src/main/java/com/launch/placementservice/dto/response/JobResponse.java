package com.launch.placementservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JobResponse {
	private String id;
	private String company;
	private String role;
	private String location;
	private String mode;
	private String packageRange;
	private String status;
}