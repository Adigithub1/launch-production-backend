package com.launch.roadmapservice.service;

import java.util.List;

public record CareerDefinition(
        List<String> domain,
        List<String> practice,
        List<String> projects) {

    public CareerDefinition {
        domain = domain == null ? List.of() : List.copyOf(domain);
        practice = practice == null ? List.of() : List.copyOf(practice);
        projects = projects == null ? List.of() : List.copyOf(projects);
    }
}