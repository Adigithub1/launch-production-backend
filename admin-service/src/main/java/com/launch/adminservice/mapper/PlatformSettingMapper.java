package com.launch.adminservice.mapper;

import org.springframework.stereotype.Component;

import com.launch.adminservice.dto.request.PlatformSettingRequest;
import com.launch.adminservice.dto.response.PlatformSettingResponse;
import com.launch.adminservice.entity.PlatformSetting;

@Component
public class PlatformSettingMapper {
	public PlatformSetting toEntity(PlatformSettingRequest x) {
		return PlatformSetting.builder().settingKey(x.getSettingKey()).settingValue(x.getSettingValue())
				.updatedBy(x.getUpdatedBy()).build();
	}

	public void update(PlatformSetting e, PlatformSettingRequest x) {
		e.setSettingKey(x.getSettingKey());
		e.setSettingValue(x.getSettingValue());
		e.setUpdatedBy(x.getUpdatedBy());
	}

	public PlatformSettingResponse toResponse(PlatformSetting x) {
		return PlatformSettingResponse.builder().id(x.getId()).settingKey(x.getSettingKey())
				.settingValue(x.getSettingValue()).updatedBy(x.getUpdatedBy()).build();
	}
}