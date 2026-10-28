package com.launch.projectservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.launch.projectservice.dto.request.ProjectRoadmapDtos.RoadmapGraph;

@Component
public class RoadmapClient {
	private final RestClient client;

	public RoadmapClient(RestClient.Builder builder,
			@Value("${services.roadmap.base-url:http://localhost:8085}") String roadmapBaseUrl) {
		this.client = builder.baseUrl(roadmapBaseUrl).build();
	}

	public RoadmapGraph get(Long studentId, String roadmapKey) {
		return client.get().uri(uriBuilder -> uriBuilder.path("/api/roadmap/student/{studentId}/graph")
				.queryParam("roadmapKey", roadmapKey).build(studentId)).retrieve().body(RoadmapGraph.class);
	}
}
