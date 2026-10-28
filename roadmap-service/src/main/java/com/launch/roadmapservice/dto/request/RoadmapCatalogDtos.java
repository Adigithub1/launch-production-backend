package com.launch.roadmapservice.dto.request;

import java.util.List;

public final class RoadmapCatalogDtos {
	private RoadmapCatalogDtos() {
	}

	public record CatalogItem(String key, String title, String category, String level, String description,
			List<String> highlights) {
	}

	public record CatalogCategory(String name, List<CatalogItem> roadmaps) {
	}
}
