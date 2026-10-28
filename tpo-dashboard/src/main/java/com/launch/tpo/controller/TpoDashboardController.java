package com.launch.tpo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.launch.tpo.dto.DashboardResponse;
import com.launch.tpo.service.TpoDashboardService;

@RestController
@RequestMapping("/api/tpo")
public class TpoDashboardController {
	private final TpoDashboardService service;

	public TpoDashboardController(TpoDashboardService service) {
		this.service = service;
	}

	@GetMapping("/dashboard")
	public DashboardResponse dashboard(@RequestHeader("X-College-Id") long collegeId,
			@RequestParam(required = false) Long cycleId) {
		return service.dashboard(collegeId, cycleId);
	}
}
