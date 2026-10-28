package com.launch.projectservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.launch.projectservice.client.RoadmapClient;
import com.launch.projectservice.dto.request.ProjectRoadmapDtos.ProgressRequest;
import com.launch.projectservice.dto.request.ProjectRoadmapDtos.ProjectPathResponse;
import com.launch.projectservice.dto.request.ProjectRoadmapDtos.RoadmapGraph;
import com.launch.projectservice.dto.request.ProjectRoadmapDtos.RoadmapNode;
import com.launch.projectservice.dto.response.ProjectResponse;
import com.launch.projectservice.entity.Project;
import com.launch.projectservice.repository.ProjectRepository;

@Service
public class ProjectSyncService {

    private final RoadmapClient roadmap;
    private final ProjectRepository repo;

    public ProjectSyncService(
            RoadmapClient roadmap,
            ProjectRepository repo) {

        this.roadmap = roadmap;
        this.repo = repo;
    }

    @Transactional
    public ProjectPathResponse sync(
            Long studentId,
            String roadmapKey) {

        String key = normalize(roadmapKey);

        System.out.println(
                "PROJECT SYNC STARTED -> studentId=" + studentId
                        + ", roadmapKey=" + key
        );

        RoadmapGraph graph = requireGraph(studentId, key);

        List<RoadmapNode> allNodes = graph.nodes() == null
                ? List.of()
                : graph.nodes();

        System.out.println(
                "ROADMAP GRAPH -> id=" + graph.id()
                        + ", targetRole=" + graph.targetRole()
                        + ", nodeCount=" + allNodes.size()
        );

        List<RoadmapNode> projectNodes = findProjectNodes(allNodes);

        /*
         * Some roadmap templates currently store every node as TOPIC.
         * If no explicit PROJECT/PRACTICE/PORTFOLIO node exists,
         * create practical projects from the final valid roadmap nodes.
         */
        if (projectNodes.isEmpty()) {
            projectNodes = fallbackProjectNodes(allNodes, 3);

            System.out.println(
                    "No explicit project nodes found. "
                            + "Using fallback nodes: "
                            + projectNodes.size()
            );
        }

        int sequence = 1;

        for (RoadmapNode node : projectNodes) {
            upsert(
                    studentId,
                    key,
                    graph,
                    node,
                    sequence++
            );
        }

        ProjectPathResponse result =
                response(studentId, key, graph);

        System.out.println(
                "PROJECT SYNC COMPLETED -> studentId=" + studentId
                        + ", roadmapKey=" + key
                        + ", projectCount=" + result.projects().size()
        );

        return result;
    }

    @Transactional
    public ProjectPathResponse get(
            Long studentId,
            String roadmapKey) {

        // Synchronize projects whenever the current roadmap is loaded.
        return sync(studentId, roadmapKey);
    }

    @Transactional
    public ProjectPathResponse progress(
            Long studentId,
            String roadmapKey,
            String nodeKey,
            ProgressRequest request) {

        String key = normalize(roadmapKey);

        if (blank(nodeKey)) {
            throw new IllegalArgumentException(
                    "nodeKey is required"
            );
        }

        if (request == null) {
            throw new IllegalArgumentException(
                    "Progress request is required"
            );
        }

        Project project =
                repo.findByStudentIdAndRoadmapKeyAndNodeKey(
                                studentId,
                                key,
                                nodeKey
                        )
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Project not found: " + nodeKey
                                )
                        );

        int requestedProgress =
                request.progress() == null
                        ? 0
                        : request.progress();

        int value = Math.max(
                0,
                Math.min(100, requestedProgress)
        );

        project.setProgress(value);

        if (Boolean.TRUE.equals(request.completed())
                || value >= 100) {

            project.setProgress(100);
            project.setStatus("COMPLETED");

        } else if (value > 0) {

            project.setStatus("IN_PROGRESS");

        } else {

            project.setStatus("NOT_STARTED");
        }

        repo.save(project);

        return response(
                studentId,
                key,
                requireGraph(studentId, key)
        );
    }

    private List<RoadmapNode> findProjectNodes(
            List<RoadmapNode> nodes) {

        List<RoadmapNode> projectNodes =
                new ArrayList<>();

        for (RoadmapNode node : nodes) {

            if (node == null || blank(node.nodeKey())) {
                continue;
            }

            if (isProjectNode(node)) {
                projectNodes.add(node);
            }
        }

        return projectNodes;
    }

    private boolean isProjectNode(RoadmapNode node) {

        if (node == null) {
            return false;
        }

        String phase = normalizeText(node.phaseTitle());
        String type = normalizeText(node.nodeType());
        String title = normalizeText(node.title());

        boolean projectType =
                type.equals("PROJECT")
                        || type.equals("PRACTICE")
                        || type.equals("CAPSTONE")
                        || type.equals("PORTFOLIO")
                        || type.equals("ASSIGNMENT");

        boolean projectPhase =
                phase.contains("PROJECT")
                        || phase.contains("PRACTICE")
                        || phase.contains("PORTFOLIO")
                        || phase.contains("CAPSTONE")
                        || phase.contains("HANDS-ON")
                        || phase.contains("HANDS ON")
                        || phase.contains("APPLIED")
                        || phase.contains("CASE STUDY")
                        || phase.contains("REAL WORLD");

        boolean projectTitle =
                title.contains("PROJECT")
                        || title.contains("CAPSTONE")
                        || title.contains("PORTFOLIO")
                        || title.contains("CASE STUDY")
                        || title.contains("PRACTICAL")
                        || title.contains("SIMULATION");

        return projectType
                || projectPhase
                || projectTitle;
    }

    private List<RoadmapNode> fallbackProjectNodes(
            List<RoadmapNode> nodes,
            int maximumProjects) {

        List<RoadmapNode> validNodes =
                nodes.stream()
                        .filter(node -> node != null)
                        .filter(node -> !blank(node.nodeKey()))
                        .filter(node -> !blank(node.title()))
                        .toList();

        if (validNodes.isEmpty()) {
            return List.of();
        }

        int numberOfProjects =
                Math.min(maximumProjects, validNodes.size());

        int startIndex =
                validNodes.size() - numberOfProjects;

        return new ArrayList<>(
                validNodes.subList(
                        startIndex,
                        validNodes.size()
                )
        );
    }

    private void upsert(
            Long studentId,
            String roadmapKey,
            RoadmapGraph graph,
            RoadmapNode node,
            int sequence) {

        Project project =
                repo.findByStudentIdAndRoadmapKeyAndNodeKey(
                                studentId,
                                roadmapKey,
                                node.nodeKey()
                        )
                        .orElseGet(Project::new);

        project.setStudentId(studentId);
        project.setStudentRoadmapId(graph.id());
        project.setRoadmapKey(roadmapKey);
        project.setNodeKey(node.nodeKey());
        project.setTargetRole(graph.targetRole());
        project.setTitle(projectTitleFor(node));
        project.setStream(graph.label());
        project.setLevel(levelFor(node.phaseTitle()));
        project.setInstructions(instructionsFor(node));
        project.setRubric(rubricFor(node));
        project.setSourceType("ROADMAP");
        project.setSequenceNumber(sequence);

        if (project.getProgress() == null) {
            project.setProgress(0);
        }

        if (blank(project.getStatus())) {
            project.setStatus("NOT_STARTED");
        }

        repo.save(project);
    }

    private String projectTitleFor(RoadmapNode node) {

        String title = node.title();

        if (blank(title)) {
            return "Applied Roadmap Project";
        }

        String normalizedTitle =
                title.toLowerCase(Locale.ROOT);

        if (normalizedTitle.contains("project")
                || normalizedTitle.contains("capstone")
                || normalizedTitle.contains("portfolio")
                || normalizedTitle.contains("case study")
                || normalizedTitle.contains("simulation")) {

            return title;
        }

        return title + " Applied Project";
    }

    private String levelFor(String phase) {

        String normalizedPhase =
                normalizeText(phase);

        if (normalizedPhase.contains("PORTFOLIO")
                || normalizedPhase.contains("CAPSTONE")) {

            return "Portfolio";
        }

        if (normalizedPhase.contains("PRACTICE")
                || normalizedPhase.contains("APPLIED")
                || normalizedPhase.contains("HANDS")) {

            return "Practice";
        }

        return "Applied";
    }

    private String instructionsFor(
            RoadmapNode node) {

        String phase =
                normalizeText(node.phaseTitle());

        if (phase.contains("PRACTICE")
                || phase.contains("APPLIED")
                || phase.contains("HANDS")) {

            return "Complete an applied exercise for "
                    + node.title()
                    + ". Record the approach, evidence, "
                    + "result and key learning.";
        }

        if (phase.contains("CASE STUDY")) {

            return "Complete a case study for "
                    + node.title()
                    + ". Document the context, analysis, "
                    + "recommendations and evidence.";
        }

        return "Build and document a portfolio-ready project for "
                + node.title()
                + ". Include the problem, approach, "
                + "implementation, evidence and outcome.";
    }

    private String rubricFor(
            RoadmapNode node) {

        return "Problem understanding; practical application; "
                + "quality of output; supporting evidence; "
                + "documentation; ability to explain decisions.";
    }

    private ProjectPathResponse response(
            Long studentId,
            String roadmapKey,
            RoadmapGraph graph) {

        List<ProjectResponse> projects =
                repo.findByStudentIdAndRoadmapKeyOrderBySequenceNumberAsc(
                                studentId,
                                roadmapKey
                        )
                        .stream()
                        .map(this::toResponse)
                        .toList();

        int overall = projects.isEmpty()
                ? 0
                : (int) Math.round(
                        projects.stream()
                                .mapToInt(project ->
                                        project.getProgress() == null
                                                ? 0
                                                : project.getProgress()
                                )
                                .average()
                                .orElse(0)
                );

        return new ProjectPathResponse(
                studentId,
                graph.id(),
                roadmapKey,
                graph.targetRole(),
                overall,
                projects
        );
    }

    private ProjectResponse toResponse(
            Project project) {

        return ProjectResponse.builder()
                .id(project.getId())
                .studentId(project.getStudentId())
                .studentRoadmapId(
                        project.getStudentRoadmapId()
                )
                .roadmapKey(project.getRoadmapKey())
                .nodeKey(project.getNodeKey())
                .targetRole(project.getTargetRole())
                .title(project.getTitle())
                .stream(project.getStream())
                .level(project.getLevel())
                .instructions(project.getInstructions())
                .rubric(project.getRubric())
                .status(project.getStatus())
                .sourceType(project.getSourceType())
                .sequenceNumber(
                        project.getSequenceNumber()
                )
                .progress(project.getProgress())
                .build();
    }

    private RoadmapGraph requireGraph(
            Long studentId,
            String roadmapKey) {

        System.out.println(
                "CALLING ROADMAP SERVICE -> studentId="
                        + studentId
                        + ", roadmapKey="
                        + roadmapKey
        );

        RoadmapGraph graph =
                roadmap.get(studentId, roadmapKey);

        if (graph == null) {
            throw new IllegalStateException(
                    "Roadmap service returned an empty graph"
            );
        }

        if (graph.nodes() == null
                || graph.nodes().isEmpty()) {

            throw new IllegalStateException(
                    "Roadmap graph contains no nodes for roadmapKey: "
                            + roadmapKey
            );
        }

        return graph;
    }

    private String normalize(String value) {

        if (blank(value)) {
            throw new IllegalArgumentException(
                    "roadmapKey is required"
            );
        }

        return value.trim()
                .toLowerCase(Locale.ROOT);
    }

    private String normalizeText(String value) {

        if (value == null) {
            return "";
        }

        return value.trim()
                .toUpperCase(Locale.ROOT);
    }

    private boolean blank(String value) {
        return value == null || value.isBlank();
    }
}