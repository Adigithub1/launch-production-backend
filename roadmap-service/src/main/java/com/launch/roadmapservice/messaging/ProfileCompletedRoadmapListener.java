package com.launch.roadmapservice.messaging;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.launch.roadmapservice.dto.request.GraphDtos.GenerateGraphRequest;
import com.launch.roadmapservice.dto.request.GraphDtos.ProfileCompletedEvent;
import com.launch.roadmapservice.service.GraphRoadmapService;

@Component
public class ProfileCompletedRoadmapListener {

    private final GraphRoadmapService service;

    public ProfileCompletedRoadmapListener(GraphRoadmapService service) {
        this.service = service;
    }

    @KafkaListener(
        topics = "launch.student-profile-completed.v1",
        groupId = "roadmap-service-graph"
    )
    public void onProfileCompleted(ProfileCompletedEvent event) {

        if (event == null || event.studentId() == null) {
            return;
        }

        String targetRole = clean(event.targetRole());

        if (targetRole.isBlank()) {
            return;
        }

        String roadmapKey = createRoadmapKey(targetRole);

        List<String> currentSkills =
            event.currentSkills() == null
                ? List.of()
                : event.currentSkills();

        GenerateGraphRequest request =
            new GenerateGraphRequest(
                roadmapKey,
                targetRole,
                clean(event.faculty()),
                clean(event.degree()),
                clean(event.branch()),
                currentSkills
            );

        service.generate(event.studentId(), request);
    }

    private String createRoadmapKey(String targetRole) {
        return clean(targetRole)
            .toLowerCase()
            .replace("&", "and")
            .replaceAll("[^a-z0-9]+", "-")
            .replaceAll("^-+|-+$", "");
    }

    private String clean(String value) {
        return value == null ? "" : value.trim();
    }
}