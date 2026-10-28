package com.launch.adminservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlatformSettingResponse {
	private String id;
	private String settingKey;
	private String settingValue;
	private String updatedBy;
}