package com.launch.placementservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobRequest {
	@jakarta.validation.constraints.NotNull
	private String company;
	@jakarta.validation.constraints.NotNull
	private String role;
	@jakarta.validation.constraints.NotNull
	private String location;
	@jakarta.validation.constraints.NotNull
	private String mode;
	@jakarta.validation.constraints.NotNull
	private String packageRange;
	@jakarta.validation.constraints.NotNull
	private String status;
}