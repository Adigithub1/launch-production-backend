package com.launch.adminservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlatformSettingRequest {
	@jakarta.validation.constraints.NotNull
	private String settingKey;
	@jakarta.validation.constraints.NotNull
	private String settingValue;
	@jakarta.validation.constraints.NotNull
	private String updatedBy;
}