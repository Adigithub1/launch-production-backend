package com.launch.learningservice.client;

import static com.launch.learningservice.dto.LearningDtos.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class RoadmapClient {

    private final RestClient restClient;

    public RoadmapClient(
            RestClient.Builder builder,
            @Value("${services.roadmap.base-url:http://localhost:8085}")
            String roadmapBaseUrl) {

        this.restClient = builder
                .baseUrl(roadmapBaseUrl)
                .build();
    }

    public RoadmapGraph get(
            Long studentId,
            String roadmapKey) {

        return restClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path(
                                    "/api/roadmap/student/{studentId}/graph"
                                )
                                .queryParam(
                                    "roadmapKey",
                                    roadmapKey
                                )
                                .build(studentId)
                )
                .retrieve()
                .body(RoadmapGraph.class);
    }

    public void done(
            Long studentId,
            String roadmapKey,
            String nodeKey) {

        restClient.put()
                .uri(uriBuilder ->
                        uriBuilder
                                .path(
                                    "/api/roadmap/student/{studentId}"
                                    + "/nodes/{nodeKey}/complete"
                                )
                                .queryParam(
                                    "roadmapKey",
                                    roadmapKey
                                )
                                .build(
                                    studentId,
                                    nodeKey
                                )
                )
                .retrieve()
                .toBodilessEntity();
    }
}