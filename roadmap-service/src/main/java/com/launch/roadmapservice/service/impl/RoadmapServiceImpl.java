package com.launch.roadmapservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.launch.roadmapservice.dto.request.GenerateRoadmapRequest;
import com.launch.roadmapservice.dto.request.RoadmapRequest;
import com.launch.roadmapservice.dto.response.RoadmapResponse;
import com.launch.roadmapservice.entity.Roadmap;
import com.launch.roadmapservice.entity.RoadmapPhase;
import com.launch.roadmapservice.exception.ResourceNotFoundException;
import com.launch.roadmapservice.mapper.RoadmapMapper;
import com.launch.roadmapservice.repository.RoadmapPhaseRepository;
import com.launch.roadmapservice.repository.RoadmapRepository;
import com.launch.roadmapservice.service.RoadmapService;

@Service
@Transactional
public class RoadmapServiceImpl implements RoadmapService {

	private final RoadmapRepository repo;
	private final RoadmapPhaseRepository phaseRepo;
	private final RoadmapMapper mapper;

	public RoadmapServiceImpl(RoadmapRepository repo, RoadmapPhaseRepository phaseRepo, RoadmapMapper mapper) {

		this.repo = repo;
		this.phaseRepo = phaseRepo;
		this.mapper = mapper;
	}

	@Override
	public RoadmapResponse create(RoadmapRequest request) {

		Roadmap roadmap = mapper.toEntity(request);
		roadmap = repo.save(roadmap);

		List<RoadmapPhase> phases = phaseRepo.findByRoadmapIdOrderByPhaseNumber(roadmap.getId());

		return mapper.toResponse(roadmap, phases);
	}

	@Override
	@Transactional(readOnly = true)
	public RoadmapResponse get(Long id) {

		Roadmap roadmap = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Roadmap not found with id: " + id));

		List<RoadmapPhase> phases = phaseRepo.findByRoadmapIdOrderByPhaseNumber(id);

		return mapper.toResponse(roadmap, phases);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RoadmapResponse> list() {

		List<Roadmap> roadmaps = repo.findAll();
		List<RoadmapResponse> responses = new ArrayList<>();

		for (Roadmap roadmap : roadmaps) {

			List<RoadmapPhase> phases = phaseRepo.findByRoadmapIdOrderByPhaseNumber(roadmap.getId());

			RoadmapResponse response = mapper.toResponse(roadmap, phases);

			responses.add(response);
		}

		return responses;
	}

	@Override
	public RoadmapResponse update(Long id, RoadmapRequest request) {

		Roadmap roadmap = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Roadmap not found with id: " + id));

		mapper.update(roadmap, request);

		roadmap = repo.save(roadmap);

		List<RoadmapPhase> phases = phaseRepo.findByRoadmapIdOrderByPhaseNumber(id);

		return mapper.toResponse(roadmap, phases);
	}

	@Override
	public void delete(Long id) {

		if (!repo.existsById(id)) {
			throw new ResourceNotFoundException("Roadmap not found with id: " + id);
		}

		phaseRepo.deleteByRoadmapId(id);
		repo.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public RoadmapResponse getByStudent(Long studentId) {

		Roadmap roadmap = repo.findByStudentId(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Roadmap not found for studentId: " + studentId));

		List<RoadmapPhase> phases = phaseRepo.findByRoadmapIdOrderByPhaseNumber(roadmap.getId());

		return mapper.toResponse(roadmap, phases);
	}

	@Override
	public RoadmapResponse generate(Long studentId, GenerateRoadmapRequest request) {

		validateGenerateRequest(studentId, request);

		Roadmap roadmap = repo.findByStudentId(studentId).orElseGet(() -> createNewRoadmap(studentId));

		roadmap.setTargetRole(request.getTargetRole());
		roadmap.setLabel(buildLabel(request));
		roadmap.setStatus("ACTIVE");

		Long currentVersion = roadmap.getVersion();

		if (currentVersion == null) {
			roadmap.setVersion(1L);
		} else {
			roadmap.setVersion(currentVersion + 1L);
		}

		roadmap = repo.save(roadmap);

		/*
		 * Clear existing phases before generating the new version.
		 */
		phaseRepo.deleteByRoadmapId(roadmap.getId());

		List<RoadmapPhase> phases = buildPhases(roadmap.getId(), request);

		phaseRepo.saveAll(phases);

		List<RoadmapPhase> savedPhases = phaseRepo.findByRoadmapIdOrderByPhaseNumber(roadmap.getId());

		return mapper.toResponse(roadmap, savedPhases);
	}

	private Roadmap createNewRoadmap(Long studentId) {

		Roadmap roadmap = new Roadmap();

		roadmap.setStudentId(studentId);
		roadmap.setVersion(0L);
		roadmap.setStatus("ACTIVE");

		return roadmap;
	}

	private void validateGenerateRequest(Long studentId, GenerateRoadmapRequest request) {

		if (studentId == null) {
			throw new IllegalArgumentException("studentId is required");
		}

		if (request == null) {
			throw new IllegalArgumentException("GenerateRoadmapRequest is required");
		}

		if (blank(request.getTargetRole())) {
			throw new IllegalArgumentException("targetRole is required");
		}
	}

	private String buildLabel(GenerateRoadmapRequest request) {

		List<String> labelParts = new ArrayList<>();

		addIfPresent(labelParts, request.getFaculty());

		String degreeOrBranch;

		if (!blank(request.getBranch())) {
			degreeOrBranch = request.getBranch();
		} else {
			degreeOrBranch = request.getDegree();
		}

		addIfPresent(labelParts, degreeOrBranch);

		if (labelParts.isEmpty()) {
			return "Career Roadmap";
		}

		return String.join(" · ", labelParts);
	}

	private void addIfPresent(List<String> values, String value) {

		if (!blank(value)) {
			values.add(value.trim());
		}
	}

	private boolean blank(String value) {

		return value == null || value.isBlank();
	}

	private List<RoadmapPhase> buildPhases(Long roadmapId, GenerateRoadmapRequest request) {

		List<String> foundation = List.of("Quantitative Aptitude", "Logical Reasoning", "Verbal Ability",
				"Communication Skills", "Resume and Interview Readiness");

		List<String> domain = domainSkills(request);

		List<String> specialization = specializationSkills(request);

		List<String> placement = List.of("Portfolio Project", "Technical Interview", "Managerial Interview",
				"HR Interview");

		String targetRole = safeTargetRole(request);

		List<RoadmapPhase> phases = new ArrayList<>();

		phases.add(createPhase(roadmapId, 1, "Placement Foundation", foundation, 0));

		phases.add(createPhase(roadmapId, 2, domainTitle(request), domain, 0));

		phases.add(createPhase(roadmapId, 3, targetRole + " Specialization", specialization, 0));

		phases.add(createPhase(roadmapId, 4, "Projects, Interview and Placement", placement, 0));

		return phases;
	}

	private RoadmapPhase createPhase(Long roadmapId, int phaseNumber, String title, List<String> skills, int progress) {

		RoadmapPhase phase = new RoadmapPhase();

		phase.setRoadmapId(roadmapId);
		phase.setPhaseNumber(phaseNumber);
		phase.setTitle(title);

		if (phaseNumber == 1) {
			phase.setStatus("IN_PROGRESS");
		} else {
			phase.setStatus("PLANNED");
		}

		phase.setProgress(progress);

		if (skills == null) {
			phase.setSkills(new ArrayList<>());
		} else {
			phase.setSkills(new ArrayList<>(skills));
		}

		return phase;
	}

	private String domainTitle(GenerateRoadmapRequest request) {

		String domain;

		if (!blank(request.getBranch())) {
			domain = request.getBranch();
		} else if (!blank(request.getDegree())) {
			domain = request.getDegree();
		} else if (!blank(request.getFaculty())) {
			domain = request.getFaculty();
		} else {
			domain = "Career";
		}

		return domain + " Domain Foundation";
	}

	private List<String> domainSkills(GenerateRoadmapRequest request) {

		String text = String
				.join(" ", Objects.toString(request.getFaculty(), ""), Objects.toString(request.getDegree(), ""),
						Objects.toString(request.getBranch(), ""), Objects.toString(request.getTargetRole(), ""))
				.toLowerCase(Locale.ROOT);

		if (text.contains("electronics") || text.contains("vlsi")) {

			return List.of("Digital Electronics", "Communication Systems", "Embedded Systems", "Microcontrollers",
					"Semiconductor Fundamentals");
		}

		if (text.contains("computer") || text.contains("software") || text.contains("developer")
				|| text.contains("information technology")) {

			return List.of("Programming", "Data Structures", "Databases", "Computer Networks", "Operating Systems");
		}

		if (text.contains("mba") || text.contains("business") || text.contains("management")) {

			return List.of("Business Communication", "Excel and Analytics", "Financial Fundamentals",
					"Marketing Fundamentals", "Strategy");
		}

		if (text.contains("commerce") || text.contains("bcom") || text.contains("accounting")) {

			return List.of("Accounting Fundamentals", "Business Economics", "Taxation Fundamentals", "Excel",
					"Financial Reporting");
		}

		return List.of("Domain Fundamentals", "Analytical Skills", "Communication", "Digital Tools",
				"Industry Fundamentals");
	}

	private List<String> specializationSkills(GenerateRoadmapRequest request) {

		String targetRole = safeTargetRole(request);
		String role = targetRole.toLowerCase(Locale.ROOT);

		if (role.contains("vlsi")) {

			return List.of("CMOS Design", "Verilog HDL", "Digital IC Design", "FPGA Fundamentals", "VLSI Verification");
		}

		if (role.contains("software") || role.contains("developer") || role.contains("java")) {

			return List.of("Java", "Spring Boot", "REST APIs", "SQL", "System Design");
		}

		if (role.contains("financial") || role.contains("finance")) {

			return List.of("Financial Modeling", "Valuation", "Corporate Finance", "Financial Analysis", "Power BI");
		}

		if (role.contains("data analyst") || role.contains("business analyst")) {

			return List.of("Advanced Excel", "SQL", "Power BI", "Data Visualization", "Business Analysis");
		}

		return List.of(targetRole + " Fundamentals", targetRole + " Tools", "Applied Project", "Case Study",
				"Interview Preparation");
	}

	private String safeTargetRole(GenerateRoadmapRequest request) {

		if (request == null || blank(request.getTargetRole())) {

			return "Career";
		}

		return request.getTargetRole().trim();
	}

	@Override
	@Transactional
	public RoadmapResponse activate(Long id) {

		Roadmap roadmap = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Roadmap not found with id: " + id));

		roadmap.setStatus("ACTIVE");

		roadmap = repo.save(roadmap);

		List<RoadmapPhase> phases = phaseRepo.findByRoadmapIdOrderByPhaseNumber(roadmap.getId());

		return mapper.toResponse(roadmap, phases);
	}
}