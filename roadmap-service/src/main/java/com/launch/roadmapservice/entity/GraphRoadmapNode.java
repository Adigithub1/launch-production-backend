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
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "graph_roadmap_nodes", uniqueConstraints = @UniqueConstraint(name = "uk_graph_node", columnNames = {
		"roadmap_id", "node_key" }))
public class GraphRoadmapNode {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "roadmap_id", nullable = false)
	private Long roadmapId;
	@Column(name = "node_key", nullable = false)
	private String nodeKey;
	@Column(nullable = false)
	private String title;
	@Column(length = 4000)
	private String description;
	@Column(name = "node_type", nullable = false)
	private String nodeType = "TOPIC";
	@Column(nullable = false)
	private String status = "LOCKED";
	@Column(name = "phase_title")
	private String phaseTitle;
	@Column(name = "display_order", nullable = false)
	private Integer displayOrder;
	@Column(name = "position_x", nullable = false)
	private Integer positionX;
	@Column(name = "position_y", nullable = false)
	private Integer positionY;
	@ElementCollection
	@CollectionTable(name = "graph_node_outcomes", joinColumns = @JoinColumn(name = "node_id"))
	@Column(name = "outcome")
	@OrderColumn(name = "display_order")
	private List<String> learningOutcomes = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long v) {
		id = v;
	}

	public Long getRoadmapId() {
		return roadmapId;
	}

	public void setRoadmapId(Long v) {
		roadmapId = v;
	}

	public String getNodeKey() {
		return nodeKey;
	}

	public void setNodeKey(String v) {
		nodeKey = v;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String v) {
		title = v;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String v) {
		description = v;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String v) {
		nodeType = v;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String v) {
		status = v;
	}

	public String getPhaseTitle() {
		return phaseTitle;
	}

	public void setPhaseTitle(String v) {
		phaseTitle = v;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer v) {
		displayOrder = v;
	}

	public Integer getPositionX() {
		return positionX;
	}

	public void setPositionX(Integer v) {
		positionX = v;
	}

	public Integer getPositionY() {
		return positionY;
	}

	public void setPositionY(Integer v) {
		positionY = v;
	}

	public List<String> getLearningOutcomes() {
		return learningOutcomes;
	}

	public void setLearningOutcomes(List<String> v) {
		learningOutcomes = v;
	}
}
