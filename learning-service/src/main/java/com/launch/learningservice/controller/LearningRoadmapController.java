package com.launch.learningservice.controller;

import static com.launch.learningservice.dto.LearningDtos.*;

import org.springframework.web.bind.annotation.*;

import com.launch.learningservice.service.LearningSyncService;
import com.launch.learningservice.service.TopicContentService;

@RestController
@RequestMapping("/api/learning/students/{studentId}")
public class LearningRoadmapController {

    private final LearningSyncService sync;
    private final TopicContentService topics;

    public LearningRoadmapController(
            LearningSyncService sync,
            TopicContentService topics) {

        this.sync = sync;
        this.topics = topics;
    }

    @PostMapping("/sync-roadmap")
    public LearningPathResponse syncRoadmap(
            @PathVariable Long studentId,
            @RequestParam String roadmapKey) {

        return sync.sync(studentId, roadmapKey);
    }

    @GetMapping("/courses")
    public LearningPathResponse courses(
            @PathVariable Long studentId,
            @RequestParam String roadmapKey) {

        return sync.get(studentId, roadmapKey);
    }

    @PutMapping("/courses/{nodeKey}/progress")
    public LearningPathResponse progress(
            @PathVariable Long studentId,
            @PathVariable String nodeKey,
            @RequestParam String roadmapKey,
            @RequestBody ProgressRequest request) {

        return sync.progress(
                studentId,
                roadmapKey,
                nodeKey,
                request
        );
    }

    @GetMapping("/topics/{nodeKey}")
    public TopicResponse topic(
            @PathVariable Long studentId,
            @PathVariable String nodeKey,
            @RequestParam String roadmapKey,
            @RequestParam String title) {

        return topics.topic(
                studentId,
                roadmapKey,
                nodeKey,
                title
        );
    }

    @PostMapping("/topics/{nodeKey}/ai")
    public AiResponse ai(
            @PathVariable Long studentId,
            @PathVariable String nodeKey,
            @RequestParam String roadmapKey,
            @RequestBody AiRequest request) {

        return topics.ask(
                studentId,
                roadmapKey,
                nodeKey,
                request
        );
    }
}