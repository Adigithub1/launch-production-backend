package com.launch.roadmapservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "graph_roadmap_edges")
public class GraphRoadmapEdge {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "roadmap_id", nullable = false)
	private Long roadmapId;
	@Column(name = "edge_key", nullable = false)
	private String edgeKey;
	@Column(name = "source_node_key", nullable = false)
	private String sourceNodeKey;
	@Column(name = "target_node_key", nullable = false)
	private String targetNodeKey;

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

	public String getEdgeKey() {
		return edgeKey;
	}

	public void setEdgeKey(String v) {
		edgeKey = v;
	}

	public String getSourceNodeKey() {
		return sourceNodeKey;
	}

	public void setSourceNodeKey(String v) {
		sourceNodeKey = v;
	}

	public String getTargetNodeKey() {
		return targetNodeKey;
	}

	public void setTargetNodeKey(String v) {
		targetNodeKey = v;
	}
}
