package com.launch.tpo.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public record DashboardResponse(Cycle cycle, Kpis kpis, List<FunnelItem> funnel, Readiness readiness,
		List<FacultyPlacement> facultyPlacement, List<UpcomingDrive> upcomingDrives, List<AtRiskStudent> atRiskStudents,
		List<ActionItem> actionItems) {
	public record Cycle(Long id, String name) {
	}

	public record Kpis(long registered, long eligible, long ready, long placed, long offers, BigDecimal averageCtcLpa,
			BigDecimal highestCtcLpa, long activeDrives) {
	}

	public record FunnelItem(String stage, long count) {
	}

	public record Readiness(int averageScore, long ready, long almostReady, long needsIntervention,
			long incompleteProfile) {
	}

	public record FacultyPlacement(String faculty, long eligible, long placed, int placementRate) {
	}

	public record UpcomingDrive(Long id, String company, String role, OffsetDateTime driveDate, String stage,
			long eligible, long applied) {
	}

	public record AtRiskStudent(Long profileId, String name, String course, String branch, int readinessScore,
			String primaryGap) {
	}

	public record ActionItem(Long id, String severity, String title, String description, OffsetDateTime dueAt) {
	}
}
