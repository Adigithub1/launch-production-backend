package com.launch.projectservice.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.launch.projectservice.dto.request.ProjectRoadmapDtos.ProgressRequest;
import com.launch.projectservice.dto.request.ProjectRoadmapDtos.ProjectPathResponse;
import com.launch.projectservice.service.ProjectSyncService;

@RestController
@RequestMapping("/api/project/students/{studentId}")
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectRoadmapController {

    private final ProjectSyncService sync;

    public ProjectRoadmapController(ProjectSyncService sync) {
        this.sync = sync;
    }

    @PostMapping("/sync-roadmap")
    public ProjectPathResponse syncRoadmap(
            @PathVariable Long studentId,
            @RequestParam String roadmapKey) {

        System.out.println(
                "PROJECT SYNC -> studentId=" + studentId
                + ", roadmapKey=" + roadmapKey
        );

        return sync.sync(studentId, roadmapKey);
    }

    @GetMapping("/projects")
    public ProjectPathResponse projects(
            @PathVariable Long studentId,
            @RequestParam String roadmapKey) {

        System.out.println(
                "GET PROJECTS -> studentId=" + studentId
                + ", roadmapKey=" + roadmapKey
        );

        return sync.get(studentId, roadmapKey);
    }

    @PutMapping("/projects/{nodeKey}/progress")
    public ProjectPathResponse progress(
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
}