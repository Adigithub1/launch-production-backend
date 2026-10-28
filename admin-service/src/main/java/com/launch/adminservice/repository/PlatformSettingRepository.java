package com.launch.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.launch.adminservice.entity.PlatformSetting;

@Repository
public interface PlatformSettingRepository extends JpaRepository<PlatformSetting, Long> {
}