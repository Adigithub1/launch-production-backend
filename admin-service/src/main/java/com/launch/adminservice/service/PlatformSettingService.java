package com.launch.adminservice.service;

import java.util.List;

import com.launch.adminservice.dto.request.PlatformSettingRequest;
import com.launch.adminservice.dto.response.PlatformSettingResponse;

public interface PlatformSettingService {
	PlatformSettingResponse create(PlatformSettingRequest r);

	PlatformSettingResponse get(Long id);

	List<PlatformSettingResponse> list();

	PlatformSettingResponse update(Long id, PlatformSettingRequest r);

	void delete(Long id);
}