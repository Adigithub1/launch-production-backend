package com.launch.roadmapservice.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.launch.roadmapservice.dto.request.GraphDtos.EdgeResponse;
import com.launch.roadmapservice.dto.request.GraphDtos.GenerateGraphRequest;
import com.launch.roadmapservice.dto.request.GraphDtos.GraphResponse;
import com.launch.roadmapservice.dto.request.GraphDtos.NodeResponse;
import com.launch.roadmapservice.dto.request.GraphDtos.ResourceResponse;
import com.launch.roadmapservice.entity.GraphRoadmap;
import com.launch.roadmapservice.entity.GraphRoadmapEdge;
import com.launch.roadmapservice.entity.GraphRoadmapNode;
import com.launch.roadmapservice.exception.ResourceNotFoundException;
import com.launch.roadmapservice.repository.GraphRoadmapEdgeRepository;
import com.launch.roadmapservice.repository.GraphRoadmapNodeRepository;
import com.launch.roadmapservice.repository.GraphRoadmapRepository;
import com.launch.roadmapservice.repository.LearningResourceRepository;
import com.launch.roadmapservice.service.TemplateGraphGenerator.EdgeSeed;
import com.launch.roadmapservice.service.TemplateGraphGenerator.GraphSeed;
import com.launch.roadmapservice.service.TemplateGraphGenerator.NodeSeed;

@Service
public class GraphRoadmapService {

	private final GraphRoadmapRepository roadmaps;
	private final GraphRoadmapNodeRepository nodes;
	private final GraphRoadmapEdgeRepository edges;
	private final LearningResourceRepository resources;
	private final TemplateGraphGenerator generator;
	private final CareerCatalogService careerCatalog;
	
	public GraphRoadmapService(
	        GraphRoadmapRepository roadmaps,
	        GraphRoadmapNodeRepository nodes,
	        GraphRoadmapEdgeRepository edges,
	        LearningResourceRepository resources,
	        TemplateGraphGenerator generator,
	        CareerCatalogService careerCatalog) {

	    this.roadmaps = roadmaps;
	    this.nodes = nodes;
	    this.edges = edges;
	    this.resources = resources;
	    this.generator = generator;
	    this.careerCatalog = careerCatalog;
	}

	// =========================================================
	// GENERATE / REGENERATE GRAPH
	// =========================================================

	@Transactional
	public GraphResponse generate(Long studentId, GenerateGraphRequest request) {

		if (studentId == null) {
			throw new IllegalArgumentException("Student ID is required.");
		}

		if (request == null) {
			throw new IllegalArgumentException("GenerateGraphRequest is required.");
		}

		String roadmapKey = clean(request.roadmapKey());

		String targetRole = clean(request.targetRole());

		if (roadmapKey.isBlank()) {
			throw new IllegalArgumentException("roadmapKey is required.");
		}

		if (targetRole.isBlank()) {
			throw new IllegalArgumentException("targetRole is required.");
		}

		/*
		 * IMPORTANT:
		 *
		 * Roadmaps are now identified by:
		 *
		 * studentId + roadmapKey
		 *
		 * and NOT just studentId.
		 */
		GraphRoadmap roadmap = roadmaps.findByStudentIdAndRoadmapKey(studentId, roadmapKey)
				.orElseGet(GraphRoadmap::new);

		roadmap.setStudentId(studentId);

		roadmap.setRoadmapKey(roadmapKey);

		roadmap.setTargetRole(targetRole);

		roadmap.setLabel(label(request));

		if (roadmap.getOverallProgress() == null) {
			roadmap.setOverallProgress(0);
		}

		roadmap = roadmaps.saveAndFlush(roadmap);

		Long roadmapId = roadmap.getId();

		/*
		 * ===================================================== DELETE PREVIOUS
		 * GENERATED GRAPH =====================================================
		 *
		 * Always delete edges before nodes.
		 *
		 * This prevents:
		 *
		 * duplicate key (roadmap_id, node_key)
		 *
		 * when Regenerate is clicked.
		 */

		edges.deleteByRoadmapId(roadmapId);

		/*
		 * If LearningResource has a FK to GraphRoadmapNode, resources must be deleted
		 * before nodes.
		 *
		 * If your repository has one of these methods:
		 *
		 * resources.deleteByRoadmapId(roadmapId);
		 *
		 * or
		 *
		 * resources.deleteByNodeId(...)
		 *
		 * perform that deletion here.
		 */

		nodes.deleteByRoadmapId(roadmapId);

		/*
		 * Force DELETE statements before new INSERT statements.
		 */
		nodes.flush();

		/*
		 * ===================================================== GENERATE CORRECT
		 * TEMPLATE =====================================================
		 *
		 * IMPORTANT:
		 *
		 * Your TemplateGraphGenerator should eventually use:
		 *
		 * roadmapKey + targetRole
		 *
		 * If your current generator only accepts targetRole, this line will compile
		 * with the current implementation.
		 */
		CareerDefinition career =
		        careerCatalog.resolve(request);

		GraphSeed seed = generator.generate(
		        roadmapKey,
		        targetRole,
		        career.domain(),
		        career.practice(),
		        career.projects()
		);
		
		if (seed == null) {
			throw new IllegalStateException("Roadmap generator returned no graph for " + roadmapKey);
		}

		Set<String> generatedNodeKeys = new HashSet<>();

		int displayOrder = 1;

		/*
		 * ===================================================== CREATE NODES
		 * =====================================================
		 */

		for (NodeSeed seedNode : seed.nodes()) {

			if (seedNode == null) {
				continue;
			}

			String nodeKey = clean(seedNode.key());

			if (nodeKey.isBlank()) {
				throw new IllegalStateException("Generated node has an empty nodeKey");
			}

			/*
			 * Prevent duplicate keys inside the generated template itself.
			 */
			if (!generatedNodeKeys.add(nodeKey)) {

				throw new IllegalStateException("Duplicate nodeKey generated: " + nodeKey);
			}

			GraphRoadmapNode node = new GraphRoadmapNode();

			node.setRoadmapId(roadmapId);

			node.setNodeKey(nodeKey);

			node.setTitle(seedNode.title());

			node.setDescription(seedNode.title());

			node.setPhaseTitle(seedNode.phase());

			node.setPositionX(seedNode.x());

			node.setPositionY(seedNode.y());

			node.setDisplayOrder(displayOrder++);

			/*
			 * For now your seed doesn't expose nodeType.
			 *
			 * Learning topics use TOPIC.
			 *
			 * Later TemplateGraphGenerator should return:
			 *
			 * TOPIC PROJECT INTERVIEW JOB SECTION
			 */
			node.setNodeType("TOPIC");

			node.setStatus("LOCKED");

			nodes.save(node);
		}

		/*
		 * Ensure all new nodes have been inserted before creating edges.
		 */
		nodes.flush();

		/*
		 * ===================================================== CREATE EDGES
		 * =====================================================
		 */

		Set<String> generatedEdgeKeys = new HashSet<>();

		for (EdgeSeed seedEdge : seed.edges()) {

			if (seedEdge == null) {
				continue;
			}

			String source = clean(seedEdge.source());

			String target = clean(seedEdge.target());

			if (!generatedNodeKeys.contains(source)) {

				throw new IllegalStateException("Edge references missing source node: " + source);
			}

			if (!generatedNodeKeys.contains(target)) {

				throw new IllegalStateException("Edge references missing target node: " + target);
			}

			String edgeKey = source + "--" + target;

			if (!generatedEdgeKeys.add(edgeKey)) {

				continue;
			}

			GraphRoadmapEdge edge = new GraphRoadmapEdge();

			edge.setRoadmapId(roadmapId);

			edge.setEdgeKey(edgeKey);

			edge.setSourceNodeKey(source);

			edge.setTargetNodeKey(target);

			edges.save(edge);
		}

		edges.flush();

		/*
		 * Unlock the initial/root graph nodes.
		 */
		unlockInitialNodes(roadmapId);

		return get(studentId, roadmapKey);
	}

	// =========================================================
	// GET GRAPH
	// =========================================================

	@Transactional(readOnly = true)
	public GraphResponse get(Long studentId, String roadmapKey) {

		if (studentId == null) {
			throw new IllegalArgumentException("Student ID is required.");
		}

		String key = clean(roadmapKey);

		if (key.isBlank()) {
			throw new IllegalArgumentException("roadmapKey is required.");
		}

		GraphRoadmap roadmap = roadmaps.findByStudentIdAndRoadmapKey(studentId, key)
				.orElseThrow(() -> new ResourceNotFoundException("Roadmap graph not found for " + key));

		/*
		 * You already have response(...). There is no toResponse(...) method.
		 */
		return response(roadmap);
	}

	// =========================================================
	// UPDATE NODE STATUS
	// =========================================================

	@Transactional
	public GraphResponse updateStatus(Long studentId, String roadmapKey, String nodeKey, String status) {

		if (studentId == null) {
			throw new IllegalArgumentException("Student ID is required.");
		}

		String key = clean(roadmapKey);

		if (key.isBlank()) {
			throw new IllegalArgumentException("roadmapKey is required.");
		}

		GraphRoadmap roadmap = roadmaps.findByStudentIdAndRoadmapKey(studentId, key)
				.orElseThrow(() -> new ResourceNotFoundException("Roadmap graph not found for " + key));

		GraphRoadmapNode node = nodes.findByRoadmapIdAndNodeKey(roadmap.getId(), nodeKey)
				.orElseThrow(() -> new ResourceNotFoundException("Roadmap node not found: " + nodeKey));

		String nextStatus = Set.of("AVAILABLE", "IN_PROGRESS", "COMPLETED", "SKIPPED").contains(status) ? status
				: "IN_PROGRESS";

		node.setStatus(nextStatus);

		nodes.save(node);

		/*
		 * Unlock any nodes whose prerequisites have now been completed.
		 */
		unlock(roadmap.getId());

		List<GraphRoadmapNode> allNodes = nodes.findByRoadmapIdOrderByDisplayOrder(roadmap.getId());

		long completedCount = allNodes.stream().filter(value -> "COMPLETED".equals(value.getStatus())).count();

		int progress = allNodes.isEmpty() ? 0 : (int) Math.round(completedCount * 100.0 / allNodes.size());

		roadmap.setOverallProgress(progress);

		roadmaps.save(roadmap);

		return response(roadmap);
	}

	// =========================================================
	// INITIAL NODE UNLOCK
	// =========================================================

	private void unlockInitialNodes(Long roadmapId) {

		List<GraphRoadmapNode> allNodes = nodes.findByRoadmapIdOrderByDisplayOrder(roadmapId);

		List<GraphRoadmapEdge> allEdges = edges.findByRoadmapId(roadmapId);

		Set<String> targetNodeKeys = new HashSet<>();

		for (GraphRoadmapEdge edge : allEdges) {

			targetNodeKeys.add(edge.getTargetNodeKey());
		}

		for (GraphRoadmapNode node : allNodes) {

			/*
			 * Nodes with no incoming edge are starting points.
			 */
			if (!targetNodeKeys.contains(node.getNodeKey())) {

				node.setStatus("AVAILABLE");

				nodes.save(node);
			}
		}
	}

	// =========================================================
	// DEPENDENCY UNLOCKING
	// =========================================================

	private void unlock(Long roadmapId) {

		List<GraphRoadmapNode> all = nodes.findByRoadmapIdOrderByDisplayOrder(roadmapId);

		Map<String, GraphRoadmapNode> nodeMap = new HashMap<>();

		all.forEach(node -> nodeMap.put(node.getNodeKey(), node));

		List<GraphRoadmapEdge> graphEdges = edges.findByRoadmapId(roadmapId);

		for (GraphRoadmapNode target : all) {

			if (!"LOCKED".equals(target.getStatus())) {

				continue;
			}

			List<String> prerequisites = graphEdges.stream()
					.filter(edge -> edge.getTargetNodeKey().equals(target.getNodeKey()))
					.map(GraphRoadmapEdge::getSourceNodeKey).toList();

			if (prerequisites.isEmpty()) {
				target.setStatus("AVAILABLE");

				nodes.save(target);

				continue;
			}

			boolean prerequisitesDone = prerequisites.stream().allMatch(prerequisiteKey -> {

				GraphRoadmapNode prerequisite = nodeMap.get(prerequisiteKey);

				if (prerequisite == null) {
					return false;
				}

				return "COMPLETED".equals(prerequisite.getStatus()) || "SKIPPED".equals(prerequisite.getStatus());
			});

			if (prerequisitesDone) {

				target.setStatus("AVAILABLE");

				nodes.save(target);
			}
		}
	}

	// =========================================================
	// RESPONSE
	// =========================================================

	private GraphResponse response(GraphRoadmap roadmap) {

		List<NodeResponse> nodeResponses = nodes.findByRoadmapIdOrderByDisplayOrder(roadmap.getId()).stream()
				.map(node -> new NodeResponse(node.getId(), node.getNodeKey(), node.getTitle(), node.getDescription(),
						node.getNodeType(), node.getStatus(), node.getPhaseTitle(), node.getPositionX(),
						node.getPositionY(), node.getLearningOutcomes(),
						resources.findByNodeId(node.getId()).stream()
								.map(resource -> new ResourceResponse(resource.getId(), resource.getTitle(),
										resource.getResourceType(), resource.getProvider(), resource.getUrl(),
										resource.getDurationMinutes()))
								.toList()))
				.toList();

		List<EdgeResponse> edgeResponses = edges.findByRoadmapId(roadmap.getId()).stream().map(
				edge -> new EdgeResponse(edge.getEdgeKey(), edge.getSourceNodeKey(), edge.getTargetNodeKey(), "ACTIVE"))
				.toList();

		return new GraphResponse(roadmap.getId(), roadmap.getStudentId(), roadmap.getTargetRole(), roadmap.getLabel(),
				roadmap.getOverallProgress(), nodeResponses, edgeResponses);
	}

	// =========================================================
	// ROADMAP LABEL
	// =========================================================

	private String label(GenerateGraphRequest request) {

		return java.util.stream.Stream.of(request.faculty(), request.degree(), request.branch())
				.filter(value -> value != null && !value.isBlank()).reduce((left, right) -> left + " · " + right)
				.orElse("Personalized LAUNCH roadmap");
	}

	// =========================================================
	// STRING HELPER
	// =========================================================
	


	private String clean(String value) {

		return value == null ? "" : value.trim();
	}
}