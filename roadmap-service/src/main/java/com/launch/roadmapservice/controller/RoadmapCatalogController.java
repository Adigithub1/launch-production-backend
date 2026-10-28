package com.launch.roadmapservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.launch.roadmapservice.dto.request.RoadmapCatalogDtos.CatalogCategory;
import com.launch.roadmapservice.dto.request.RoadmapCatalogDtos.CatalogItem;
import com.launch.roadmapservice.service.RoadmapCatalogService;

@RestController
@RequestMapping("/api/roadmap/catalog")
@CrossOrigin(origins = "http://localhost:5173")
public class RoadmapCatalogController {
	private final RoadmapCatalogService service;

	public RoadmapCatalogController(RoadmapCatalogService service) {
		this.service = service;
	}

	@GetMapping
	public List<CatalogCategory> all() {
		return service.categories();
	}

	@GetMapping("/{key}")
	public CatalogItem one(@PathVariable String key) {
		return service.get(key);
	}
}
