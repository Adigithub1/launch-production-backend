package com.launch.learningservice.service;

import static com.launch.learningservice.dto.LearningDtos.*;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class TopicContentService {
	public TopicResponse topic(Long studentId, String roadmapKey, String nodeKey, String title) {
		return new TopicResponse(nodeKey, title, "Approved content has not yet been published for this topic.",
				List.of(), "", List.of());
	}

	public AiResponse ask(Long studentId, String roadmapKey, String nodeKey, AiRequest request) {
		throw new UnsupportedOperationException(
				"Configure an AI Tutor provider and retrieval source before enabling real-time answers.");
	}
}
