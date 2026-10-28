package com.launch.roadmapservice.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhaseResponse {

	private Long id;

	private Integer phaseNumber;

	private String period;

	private String phase;

	private String status;

	private Integer progress;

	private List<String> skills;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPhaseNumber() {
		return phaseNumber;
	}

	public void setPhaseNumber(Integer phaseNumber) {
		this.phaseNumber = phaseNumber;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "PhaseResponse [id=" + id + ", phaseNumber=" + phaseNumber + ", period=" + period + ", phase=" + phase
				+ ", status=" + status + ", progress=" + progress + ", skills=" + skills + "]";
	}

}