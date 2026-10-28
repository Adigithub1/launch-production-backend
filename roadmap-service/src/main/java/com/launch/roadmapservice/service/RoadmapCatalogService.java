package com.launch.roadmapservice.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.launch.roadmapservice.dto.request.RoadmapCatalogDtos.CatalogCategory;
import com.launch.roadmapservice.dto.request.RoadmapCatalogDtos.CatalogItem;

@Service
public class RoadmapCatalogService {
	private final List<CatalogItem> items = List.of(
			item("frontend", "Frontend Developer", "Web Development", "Beginner to Advanced",
					"Build accessible, responsive web applications.", "HTML", "CSS", "JavaScript", "React", "Testing"),
			item("backend", "Backend Developer", "Web Development", "Beginner to Advanced",
					"Build secure services, APIs and distributed systems.", "Java", "Spring Boot", "SQL", "REST",
					"Security"),
			item("fullstack", "Full Stack Developer", "Web Development", "Beginner to Advanced",
					"Build complete frontend and backend products.", "React", "Java", "Spring Boot", "PostgreSQL",
					"Deployment"),
			item("ai-engineer", "AI Engineer", "AI & Machine Learning", "Intermediate to Advanced",
					"Build machine learning and generative AI systems.", "Python", "Machine Learning", "Deep Learning",
					"RAG", "MLOps"),
			item("data-analyst", "Data Analyst", "Data & Analytics", "Beginner to Advanced",
					"Turn data into reports, dashboards and decisions.", "Excel", "SQL", "Statistics", "Power BI",
					"Python"),
			item("devops", "DevOps Engineer", "Cloud & DevOps", "Intermediate to Advanced",
					"Automate delivery and operate reliable platforms.", "Linux", "Git", "Docker", "CI/CD",
					"Kubernetes"),
			item("cybersecurity", "Cybersecurity Analyst", "Security", "Beginner to Advanced",
					"Monitor, investigate and reduce security risk.", "Networking", "Linux", "SIEM",
					"Incident Response", "Cloud Security"),
			item("accountant", "Accountant", "Finance & Commerce", "Beginner to Advanced",
					"Build accounting, compliance and analysis capability.", "Bookkeeping", "Excel", "Taxation", "ERP",
					"Financial Analysis"),
			item("marketing-manager", "Marketing Manager", "Management", "Beginner to Advanced",
					"Plan, execute and measure modern marketing programs.", "Research", "Brand", "Digital Marketing",
					"Analytics", "Campaigns"));

	private CatalogItem item(String key, String title, String category, String level, String description,
			String... highlights) {
		return new CatalogItem(key, title, category, level, description, List.of(highlights));
	}

	public List<CatalogCategory> categories() {
		Map<String, List<CatalogItem>> grouped = new LinkedHashMap<>();
		for (CatalogItem i : items)
			grouped.computeIfAbsent(i.category(), k -> new ArrayList<>()).add(i);
		return grouped.entrySet().stream().map(e -> new CatalogCategory(e.getKey(), e.getValue())).toList();
	}

	public CatalogItem get(String key) {
		return items.stream().filter(i -> i.key().equalsIgnoreCase(key)).findFirst()
				.orElseThrow(() -> new NoSuchElementException("Roadmap not found: " + key));
	}

	public String roleForKey(String key) {
		return get(key).title();
	}
}
