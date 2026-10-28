package com.launch.roadmapservice.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roadmap_phases")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoadmapPhase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "roadmap_id", nullable = false)
	private Long roadmapId;
	@Column(name = "phase_number", nullable = false)
	private Integer phaseNumber;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String status;
	@Column(nullable = false)
	private Integer progress;
	@ElementCollection
	@CollectionTable(name = "roadmap_phase_skills", joinColumns = @JoinColumn(name = "phase_id"))
	@Column(name = "skill_name")
	@OrderColumn(name = "display_order")
	@Builder.Default
	private List<String> skills = new ArrayList<>();

	@Override
	public String toString() {
		return "RoadmapPhase [id=" + id + ", roadmapId=" + roadmapId + ", phaseNumber=" + phaseNumber + ", title="
				+ title + ", status=" + status + ", progress=" + progress + ", skills=" + skills + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoadmapId() {
		return roadmapId;
	}

	public void setRoadmapId(Long roadmapId) {
		this.roadmapId = roadmapId;
	}

	public Integer getPhaseNumber() {
		return phaseNumber;
	}

	public void setPhaseNumber(Integer phaseNumber) {
		this.phaseNumber = phaseNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

}
