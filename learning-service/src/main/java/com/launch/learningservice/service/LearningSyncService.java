package com.launch.learningservice.service;

import static com.launch.learningservice.dto.LearningDtos.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.launch.learningservice.catalog.CurriculumCatalog;
import com.launch.learningservice.client.RoadmapClient;
import com.launch.learningservice.entity.LearningCourse;
import com.launch.learningservice.repository.LearningCourseRepository;

@Service
public class LearningSyncService {

    private final RoadmapClient roadmap;
    private final CurriculumCatalog catalog;
    private final LearningCourseRepository repo;

    public LearningSyncService(
            RoadmapClient roadmap,
            CurriculumCatalog catalog,
            LearningCourseRepository repo) {
        this.roadmap = roadmap;
        this.catalog = catalog;
        this.repo = repo;
    }

    @Transactional
    public LearningPathResponse sync(Long studentId, String roadmapKey) {
        String key = normalizeRoadmapKey(roadmapKey);
        RoadmapGraph graph = requireGraph(studentId, key);

        int sequence = 1;
        Set<String> seen = new HashSet<>();

        /*
         * Add shared placement foundation courses first.
         * Duplicate node keys are skipped when the graph already contains them.
         */
        List<CurriculumCatalog.Course> foundations = catalog.foundation();
        if (foundations != null) {
            for (CurriculumCatalog.Course foundation : foundations) {
                if (!validKey(foundation == null ? null : foundation.nodeKey())) {
                    continue;
                }
                if (!seen.add(foundation.nodeKey())) {
                    continue;
                }
                upsert(studentId, key, graph, foundation, sequence++, "FOUNDATION");
            }
        }

        /*
         * Synchronize every learning-capable roadmap node.
         * GraphRoadmapService currently emits TOPIC nodes but does not expose a
         * clickable field, so clickable must not be required here.
         */
        if (graph.nodes() != null) {
            for (RoadmapNode node : graph.nodes()) {
                if (!isLearning(node)) {
                    continue;
                }
                if (!validKey(node.nodeKey()) || !seen.add(node.nodeKey())) {
                    continue;
                }

                CurriculumCatalog.Course course = catalog.enrich(
                        key,
                        node.nodeKey(),
                        node.title(),
                        node.phaseTitle());

                if (course == null) {
                    continue;
                }

                upsert(studentId, key, graph, course, sequence++, "ROADMAP");
            }
        }

        return response(studentId, key, graph);
    }

    @Transactional
    public LearningPathResponse get(Long studentId, String roadmapKey) {
        /*
         * Always synchronize before returning.
         * Previously synchronization ran only when the table was empty. Once four
         * foundation courses existed, newly generated roadmap nodes were never added.
         * Upsert preserves existing progress/status for matching node keys.
         */
        return sync(studentId, roadmapKey);
    }

    @Transactional
    public LearningPathResponse progress(
            Long studentId,
            String roadmapKey,
            String nodeKey,
            ProgressRequest request) {

        String key = normalizeRoadmapKey(roadmapKey);

        if (!validKey(nodeKey)) {
            throw new IllegalArgumentException("nodeKey is required");
        }
        if (request == null) {
            throw new IllegalArgumentException("Progress request is required");
        }

        RoadmapGraph graph = requireGraph(studentId, key);

        LearningCourse course = repo
                .findByStudentIdAndRoadmapKeyAndNodeKey(studentId, key, nodeKey)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Learning course not found for studentId=" + studentId
                                + ", roadmapKey=" + key
                                + ", nodeKey=" + nodeKey));

        int nextProgress = Math.max(
                0,
                Math.min(100, request.progress() == null ? 0 : request.progress()));

        course.setProgress(nextProgress);

        boolean completed = nextProgress >= 100
                && Boolean.TRUE.equals(request.quizPassed());

        if (completed) {
            course.setStatus("COMPLETED");
        } else if (nextProgress > 0) {
            course.setStatus("IN_PROGRESS");
        } else {
            course.setStatus("NOT_STARTED");
        }

        repo.save(course);

        if ("COMPLETED".equals(course.getStatus())
                && "ROADMAP".equals(course.getSourceType())) {
            roadmap.done(studentId, key, nodeKey);
        }

        RoadmapGraph updatedGraph = requireGraph(studentId, key);
        return response(studentId, key, updatedGraph);
    }

    private boolean isLearning(RoadmapNode node) {
        if (node == null) {
            return false;
        }

        String nodeType = node.nodeType() == null
                ? ""
                : node.nodeType().trim().toUpperCase();

        /*
         * Do not require Boolean.TRUE.equals(node.clickable()).
         * The roadmap graph response used by LAUNCH does not currently provide
         * clickable, so the value is null and the old filter rejected every node.
         */
        return "TOPIC".equals(nodeType)
                || "LEARNING".equals(nodeType)
                || "SUBTOPIC".equals(nodeType);
    }

    private void upsert(
            Long studentId,
            String roadmapKey,
            RoadmapGraph graph,
            CurriculumCatalog.Course seed,
            int sequence,
            String sourceType) {

        if (seed == null || !validKey(seed.nodeKey())) {
            throw new IllegalArgumentException("Course seed/nodeKey is required");
        }

        LearningCourse course = repo
                .findByStudentIdAndRoadmapKeyAndNodeKey(
                        studentId,
                        roadmapKey,
                        seed.nodeKey())
                .orElseGet(LearningCourse::new);

        course.setStudentId(studentId);
        course.setStudentRoadmapId(graph.studentRoadmapId());
        course.setRoadmapKey(roadmapKey);
        course.setNodeKey(seed.nodeKey());
        course.setTitle(seed.title());
        course.setCategory(seed.category());
        course.setDifficulty(seed.difficulty());
        course.setDurationMinutes(seed.durationMinutes());
        course.setSequenceNumber(sequence);
        course.setSourceType(sourceType);

        if (course.getProgress() == null) {
            course.setProgress(0);
        }
        if (course.getStatus() == null || course.getStatus().isBlank()) {
            course.setStatus("NOT_STARTED");
        }

        repo.saveAndFlush(course);
    }

    private LearningPathResponse response(
            Long studentId,
            String roadmapKey,
            RoadmapGraph graph) {

        List<CourseResponse> courses = repo
                .findByStudentIdAndRoadmapKeyOrderBySequenceNumberAsc(
                        studentId,
                        roadmapKey)
                .stream()
                .map(course -> new CourseResponse(
                        course.getId(),
                        course.getNodeKey(),
                        course.getTitle(),
                        course.getCategory(),
                        course.getDifficulty(),
                        course.getDurationMinutes(),
                        course.getSequenceNumber(),
                        course.getProgress(),
                        course.getStatus(),
                        course.getSourceType()))
                .toList();

        int averageProgress = courses.isEmpty()
                ? 0
                : (int) Math.round(
                        courses.stream()
                                .mapToInt(course -> course.progress() == null
                                        ? 0
                                        : course.progress())
                                .average()
                                .orElse(0));

        return new LearningPathResponse(
                studentId,
                graph.studentRoadmapId(),
                roadmapKey,
                graph.targetRole(),
                averageProgress,
                courses);
    }

    private RoadmapGraph requireGraph(Long studentId, String roadmapKey) {
        RoadmapGraph graph = roadmap.get(studentId, roadmapKey);
        if (graph == null) {
            throw new IllegalStateException("Roadmap service returned an empty graph");
        }
        return graph;
    }

    private boolean validKey(String value) {
        return value != null && !value.isBlank();
    }

    private String normalizeRoadmapKey(String roadmapKey) {
        if (!validKey(roadmapKey)) {
            throw new IllegalArgumentException("roadmapKey is required");
        }
        return roadmapKey.trim().toLowerCase();
    }
}
