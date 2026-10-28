package com.launch.adminservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.launch.adminservice.dto.request.PlatformSettingRequest;
import com.launch.adminservice.dto.response.PlatformSettingResponse;
import com.launch.adminservice.exception.ResourceNotFoundException;
import com.launch.adminservice.mapper.PlatformSettingMapper;
import com.launch.adminservice.repository.PlatformSettingRepository;
import com.launch.adminservice.service.PlatformSettingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PlatformSettingServiceImpl implements PlatformSettingService {
	private final PlatformSettingRepository repo;
	private final PlatformSettingMapper mapper;

	public PlatformSettingResponse create(PlatformSettingRequest r) {
		return mapper.toResponse(repo.save(mapper.toEntity(r)));
	}

	@Transactional(readOnly = true)
	public PlatformSettingResponse get(Long id) {
		return mapper.toResponse(
				repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("PlatformSetting not found")));
	}

	@Transactional(readOnly = true)
	public List<PlatformSettingResponse> list() {
		return repo.findAll().stream().map(mapper::toResponse).toList();
	}

	public PlatformSettingResponse update(Long id, PlatformSettingRequest r) {
		var e = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("PlatformSetting not found"));
		mapper.update(e, r);
		return mapper.toResponse(repo.save(e));
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}