package com.launch.roadmapservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "learning_resources")
public class LearningResource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "node_id", nullable = false)
	private Long nodeId;
	@Column(nullable = false)
	private String title;
	@Column(name = "resource_type", nullable = false)
	private String resourceType;
	private String provider;
	@Column(length = 1500)
	private String url;
	@Column(name = "duration_minutes")
	private Integer durationMinutes;

	public Long getId() {
		return id;
	}

	public void setId(Long v) {
		id = v;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long v) {
		nodeId = v;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String v) {
		title = v;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String v) {
		resourceType = v;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String v) {
		provider = v;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String v) {
		url = v;
	}

	public Integer getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(Integer v) {
		durationMinutes = v;
	}
}
