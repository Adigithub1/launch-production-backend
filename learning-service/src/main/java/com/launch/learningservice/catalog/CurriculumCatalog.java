package com.launch.learningservice.catalog;

import java.io.InputStream;
import java.util.*;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CurriculumCatalog {
	public record Catalog(Integer version, List<Course> foundationCourses, Map<String, Roadmap> roadmaps) {
	}

	public record Roadmap(String faculty, String branch, String targetRole, List<Course> courses) {
	}

	public record Course(String nodeKey, String title, String category, Integer durationMinutes, String difficulty) {
	}

	private final Catalog catalog;

	public CurriculumCatalog(ObjectMapper mapper) {
		try (InputStream in = getClass().getResourceAsStream("/catalog/curriculum-catalog.json")) {
			catalog = mapper.readValue(in, Catalog.class);
		} catch (Exception e) {
			throw new IllegalStateException("Unable to read curriculum catalog", e);
		}
	}

	public List<Course> foundation() {
		return catalog.foundationCourses();
	}

	public Optional<Roadmap> roadmap(String key) {
		return Optional.ofNullable(catalog.roadmaps().get(key));
	}

	public Course enrich(String roadmapKey, String nodeKey, String title, String category) {
		return roadmap(roadmapKey)
				.flatMap(r -> r.courses().stream().filter(c -> c.nodeKey().equals(nodeKey)).findFirst())
				.orElse(new Course(nodeKey, title, category, 240, "INTERMEDIATE"));
	}
}
