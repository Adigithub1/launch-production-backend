package com.launch.roadmapservice.service;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.launch.roadmapservice.dto.request.GraphDtos.GenerateGraphRequest;

@Service
public class CareerCatalogService {

    public CareerDefinition resolve(GenerateGraphRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("GenerateGraphRequest is required.");
        }

        String roadmapKey = normalize(request.roadmapKey());
        String targetRole = normalize(request.targetRole());
        String faculty = normalize(request.faculty());
        String degree = normalize(request.degree());
        String branch = normalize(request.branch());

        System.out.println("CAREER CATALOG RESOLVE"
                + " | roadmapKey=" + roadmapKey
                + " | targetRole=" + targetRole
                + " | faculty=" + faculty
                + " | degree=" + degree
                + " | branch=" + branch);

        /* Exact careers always win over branch or degree fallbacks. */

        if (isCareer(roadmapKey, targetRole, "full-stack-engineer", "full-stack-developer")) {
            return fullStack();
        }
        if (isCareer(roadmapKey, targetRole, "backend-developer", "java-developer", "spring-boot-developer")) {
            return backend();
        }
        if (isCareer(roadmapKey, targetRole, "web-developer", "frontend-developer", "react-developer")) {
            return frontend();
        }
        if (isCareer(roadmapKey, targetRole, "software-developer", "software-engineer")) {
            return software();
        }
        if (isCareer(roadmapKey, targetRole, "ai-engineer")) {
            return aiEngineer();
        }
        if (isCareer(roadmapKey, targetRole, "machine-learning-engineer", "ml-engineer")) {
            return machineLearning();
        }
        if (isCareer(roadmapKey, targetRole, "data-analyst", "business-intelligence-analyst", "statistical-analyst")) {
            return dataAnalyst();
        }
        if (isCareer(roadmapKey, targetRole, "business-analyst", "product-analyst", "strategy-analyst")) {
            return businessAnalyst();
        }
        if (isCareer(roadmapKey, targetRole, "data-engineer")) {
            return dataEngineer();
        }
        if (isCareer(roadmapKey, targetRole, "cloud-engineer")) {
            return cloudEngineer();
        }
        if (isCareer(roadmapKey, targetRole, "devops-engineer", "site-reliability-engineer", "sre")) {
            return devOps();
        }
        if (isCareer(roadmapKey, targetRole, "cybersecurity-analyst", "cyber-security-analyst", "security-analyst")) {
            return cyberSecurity();
        }
        if (isCareer(roadmapKey, targetRole, "qa-engineer", "qa-automation-engineer", "software-tester", "test-engineer", "testing-engineer")) {
            return qa();
        }
        if (isCareer(roadmapKey, targetRole, "technical-support-engineer")) {
            return technicalSupport();
        }

        /* Core engineering roles. */
        if (isCareer(roadmapKey, targetRole, "design-engineer", "mechanical-design-engineer")) {
            return mechanicalDesign();
        }
        if (isCareer(roadmapKey, targetRole, "production-engineer", "manufacturing-engineer")) {
            return manufacturing();
        }
        if (isCareer(roadmapKey, targetRole, "quality-engineer", "quality-analyst", "quality-technician")) {
            return engineeringQuality();
        }
        if (isCareer(roadmapKey, targetRole, "maintenance-engineer", "maintenance-technician", "service-technician")) {
            return maintenance();
        }
        if (isCareer(roadmapKey, targetRole, "operations-engineer")) {
            return engineeringOperations();
        }
        if (isCareer(roadmapKey, targetRole, "site-engineer")) {
            return siteEngineering();
        }
        if (isCareer(roadmapKey, targetRole, "structural-engineer")) {
            return structuralEngineering();
        }
        if (isCareer(roadmapKey, targetRole, "quantity-surveyor")) {
            return quantitySurveying();
        }
        if (isCareer(roadmapKey, targetRole, "planning-engineer", "survey-engineer")) {
            return civilPlanning();
        }
        if (isCareer(roadmapKey, targetRole, "environmental-engineer")) {
            return environmentalEngineering();
        }
        if (isCareer(roadmapKey, targetRole, "electrical-design-engineer", "power-systems-engineer", "control-engineer", "energy-analyst")) {
            return electricalEngineering();
        }
        if (isCareer(roadmapKey, targetRole, "embedded-systems-engineer", "electronics-design-engineer", "iot-engineer")) {
            return embeddedSystems();
        }
        if (isCareer(roadmapKey, targetRole, "vlsi-engineer")) {
            return vlsi();
        }
        if (isCareer(roadmapKey, targetRole, "telecom-engineer")) {
            return telecom();
        }
        if (isCareer(roadmapKey, targetRole, "process-engineer", "plant-engineer", "safety-engineer")) {
            return chemicalProcess();
        }
        if (isCareer(roadmapKey, targetRole, "automotive-design-engineer", "ev-engineer", "service-engineer")) {
            return automotive();
        }
        if (isCareer(roadmapKey, targetRole, "bioprocess-engineer", "biomedical-engineer")) {
            return biotechEngineering();
        }

        /* Finance, commerce, management and operations. */
        if (isCareer(roadmapKey, targetRole, "accountant", "audit-associate", "tax-analyst")) {
            return accounting();
        }
        if (isCareer(roadmapKey, targetRole, "financial-analyst", "investment-analyst", "credit-analyst", "finance-business-partner", "banking-associate")) {
            return finance();
        }
        if (isCareer(roadmapKey, targetRole, "risk-analyst")) {
            return risk();
        }
        if (isCareer(roadmapKey, targetRole, "hr-generalist", "hr-executive", "hr-analyst")) {
            return hrGeneralist();
        }
        if (isCareer(roadmapKey, targetRole, "talent-acquisition-specialist")) {
            return talentAcquisition();
        }
        if (isCareer(roadmapKey, targetRole, "learning-and-development-executive", "learning-and-development-associate", "training-coordinator")) {
            return learningDevelopment();
        }
        if (isCareer(roadmapKey, targetRole, "employee-relations-executive", "compensation-analyst")) {
            return employeeRelations();
        }
        if (isCareer(roadmapKey, targetRole, "marketing-analyst", "market-research-analyst")) {
            return marketingAnalytics();
        }
        if (isCareer(roadmapKey, targetRole, "digital-marketing-executive", "marketing-executive", "social-media-executive")) {
            return digitalMarketing();
        }
        if (isCareer(roadmapKey, targetRole, "brand-executive", "product-marketing-associate")) {
            return brandMarketing();
        }
        if (isCareer(roadmapKey, targetRole, "sales-manager", "sales-analyst", "sales-executive", "business-development-manager", "business-development-executive")) {
            return salesBusinessDevelopment();
        }
        if (isCareer(roadmapKey, targetRole, "operations-analyst", "operations-executive", "project-coordinator")) {
            return businessOperations();
        }
        if (isCareer(roadmapKey, targetRole, "supply-chain-analyst", "logistics-executive", "logistics-coordinator", "global-sourcing-analyst", "procurement-executive")) {
            return supplyChain();
        }
        if (isCareer(roadmapKey, targetRole, "international-business-executive", "export-manager", "trade-analyst")) {
            return internationalBusiness();
        }
        if (isCareer(roadmapKey, targetRole, "entrepreneur", "startup-operations-associate", "innovation-analyst", "venture-analyst", "product-associate")) {
            return entrepreneurship();
        }
        if (isCareer(roadmapKey, targetRole, "compliance-analyst", "regulatory-affairs-associate")) {
            return compliance();
        }

        /* Humanities, content, communication, policy and social impact. */
        if (isCareer(roadmapKey, targetRole, "content-strategist", "content-executive", "content-producer", "education-content-developer")) {
            return contentStrategy();
        }
        if (isCareer(roadmapKey, targetRole, "copywriter", "journalism-trainee")) {
            return journalismWriting();
        }
        if (isCareer(roadmapKey, targetRole, "communications-executive", "communications-specialist", "public-relations-associate", "public-relations-executive")) {
            return communicationsPr();
        }
        if (isCareer(roadmapKey, targetRole, "policy-analyst", "policy-associate", "policy-research-assistant")) {
            return policyResearch();
        }
        if (isCareer(roadmapKey, targetRole, "research-assistant", "research-associate", "social-research-assistant", "economic-research-analyst", "research-analyst")) {
            return research();
        }
        if (isCareer(roadmapKey, targetRole, "csr-executive", "corporate-social-responsibility-executive", "program-coordinator", "community-development-officer", "casework-coordinator")) {
            return socialImpact();
        }
        if (isCareer(roadmapKey, targetRole, "behavioural-research-assistant", "market-research-associate", "counselling-coordinator")) {
            return behaviouralResearch();
        }

        /* Design, architecture and planning. */
        if (isCareer(roadmapKey, targetRole, "ui-ux-designer", "product-design-associate", "design-researcher")) {
            return uxDesign();
        }
        if (isCareer(roadmapKey, targetRole, "graphic-designer", "visual-designer", "visualization-artist")) {
            return visualDesign();
        }
        if (isCareer(roadmapKey, targetRole, "junior-architect", "interior-design-associate", "urban-design-assistant")) {
            return architecture();
        }
        if (isCareer(roadmapKey, targetRole, "bim-associate")) {
            return bim();
        }
        if (isCareer(roadmapKey, targetRole, "urban-planning-assistant", "transport-planning-assistant", "infrastructure-planning-analyst", "housing-research-associate")) {
            return urbanPlanning();
        }
        if (isCareer(roadmapKey, targetRole, "gis-analyst")) {
            return gis();
        }

        /* Healthcare, pharmacy and life sciences. */
        if (isCareer(roadmapKey, targetRole, "quality-assurance-associate", "quality-control-analyst")) {
            return regulatedQuality();
        }
        if (isCareer(roadmapKey, targetRole, "clinical-data-associate", "clinical-research-coordinator")) {
            return clinicalDataResearch();
        }
        if (isCareer(roadmapKey, targetRole, "medical-representative")) {
            return medicalSales();
        }
        if (isCareer(roadmapKey, targetRole, "clinical-operations-coordinator", "patient-care-coordinator", "healthcare-quality-executive", "hospital-operations-executive", "patient-services-manager")) {
            return healthcareOperations();
        }
        if (isCareer(roadmapKey, targetRole, "healthcare-analyst", "healthcare-consultant", "healthcare-project-coordinator", "quality-executive")) {
            return healthcareAnalytics();
        }
        if (isCareer(roadmapKey, targetRole, "laboratory-analyst", "food-safety-analyst")) {
            return laboratoryScience();
        }

        /* Agriculture, hospitality, tourism and education. */
        if (isCareer(roadmapKey, targetRole, "agriculture-field-officer", "agribusiness-executive")) {
            return agriculture();
        }
        if (isCareer(roadmapKey, targetRole, "hotel-operations-executive", "front-office-executive", "food-and-beverage-executive", "guest-relations-executive", "revenue-management-assistant")) {
            return hospitality();
        }
        if (isCareer(roadmapKey, targetRole, "travel-operations-executive", "tour-coordinator", "destination-marketing-executive", "customer-experience-executive", "travel-consultant")) {
            return tourism();
        }
        if (isCareer(roadmapKey, targetRole, "academic-coordinator", "instructional-design-associate", "student-success-associate")) {
            return education();
        }

        /* Vocational and general roles. */
        if (isCareer(roadmapKey, targetRole, "technician-trainee", "production-associate")) {
            return vocational();
        }
        if (isCareer(roadmapKey, targetRole, "customer-success-executive")) {
            return customerSuccess();
        }

        /* Branch fallbacks run only when no exact career matched. */
        if (matches(branch, "cse", "computer-science", "information-technology")) {
            return software();
        }
        if (matches(branch, "mechanical")) {
            return mechanicalDesign();
        }
        if (matches(branch, "civil")) {
            return siteEngineering();
        }
        if (matches(branch, "electrical")) {
            return electricalEngineering();
        }
        if (matches(branch, "ece", "electronics")) {
            return embeddedSystems();
        }
        if (matches(branch, "chemical")) {
            return chemicalProcess();
        }
        if (matches(branch, "automobile", "automotive")) {
            return automotive();
        }
        if (matches(branch, "biotechnology", "biomedical")) {
            return biotechEngineering();
        }

        /* Degree/faculty fallbacks. */
        if (matchesEither(faculty, degree, "bca", "mca")) return software();
        if (matchesEither(faculty, degree, "bcom", "commerce")) return accounting();
        if (matchesEither(faculty, degree, "bba", "mba", "management")) return businessOperations();
        if (matchesEither(faculty, degree, "law")) return compliance();
        if (matchesEither(faculty, degree, "pharmacy")) return regulatedQuality();
        if (matchesEither(faculty, degree, "nursing", "healthcare")) return healthcareOperations();
        if (matchesEither(faculty, degree, "agriculture")) return agriculture();
        if (matchesEither(faculty, degree, "architecture")) return architecture();
        if (matchesEither(faculty, degree, "planning")) return urbanPlanning();
        if (matchesEither(faculty, degree, "design")) return uxDesign();
        if (matchesEither(faculty, degree, "hotel", "hospitality")) return hospitality();
        if (matchesEither(faculty, degree, "tourism")) return tourism();
        if (matchesEither(faculty, degree, "education")) return education();
        if (matchesEither(faculty, degree, "statistics", "mathematics")) return dataAnalyst();
        if (matchesEither(faculty, degree, "microbiology", "life-sciences")) return laboratoryScience();
        if (matchesEither(faculty, degree, "socialwork", "social-work")) return socialImpact();
        if (matchesEither(faculty, degree, "masscomm", "journalism")) return journalismWriting();
        if (matchesEither(faculty, degree, "psychology")) return behaviouralResearch();
        if (matchesEither(faculty, degree, "economics")) return research();
        if (matchesEither(faculty, degree, "ba", "ma", "humanities", "bsc")) return research();
        if (matchesEither(faculty, degree, "diploma", "vocational")) return vocational();

        String roleName = clean(request.targetRole());
        if (roleName.isBlank()) roleName = "Career";
        return definition(
                List.of(roleName + " Fundamentals", "Industry Knowledge", "Professional Practices", "Digital and Data Literacy", "Ethics and Communication"),
                List.of("Applied Problem Solving", "Industry Case Studies", "Professional Assessment"),
                List.of(roleName + " Capstone Project"));
    }

    private CareerDefinition fullStack() { return definition(
            List.of("Internet, Browsers and HTTP", "Semantic HTML and Accessibility", "CSS and Responsive Design", "Modern JavaScript", "Git and Developer Tooling", "React Frontend Development", "State Management and Data Fetching", "Frontend Testing", "Backend Development Fundamentals", "Java and Spring Boot", "REST API Design", "PostgreSQL and Persistence", "Authentication, Authorization and Security", "Full Stack Integration", "Testing and Code Quality", "Docker, CI/CD and Cloud Deployment", "System Design Foundations"),
            List.of("JavaScript Challenges", "React Component Exercises", "SQL and API Exercises", "Frontend and Backend Debugging", "Authentication Integration Lab", "Full Stack Integration Lab"),
            List.of("Responsive React Frontend", "Spring Boot REST API", "Database-Backed Full Stack Application", "Production Full Stack Capstone")); }

    private CareerDefinition frontend() { return definition(
            List.of("Internet, Browsers and HTTP", "Semantic HTML", "Accessibility", "CSS Fundamentals", "Flexbox and Grid", "Responsive Design", "Modern JavaScript", "DOM and Browser APIs", "Git and Package Management", "React", "Routing and Forms", "State Management", "API Integration", "Frontend Testing", "Performance and Web Security", "Deployment"),
            List.of("HTML and CSS Challenges", "JavaScript Exercises", "React Component Labs", "Accessibility Review", "Frontend Debugging"),
            List.of("Responsive Portfolio", "React Application", "Production Frontend Capstone")); }

    private CareerDefinition backend() { return definition(
            List.of("Internet, DNS and HTTP", "Core Java", "Object-Oriented Programming and Design Principles", "Data Structures and Algorithms", "Git and GitHub", "SQL and Relational Databases", "PostgreSQL", "NoSQL Fundamentals", "Spring Boot", "REST API Design", "Validation and Error Handling", "Spring Data JPA and Hibernate", "Authentication and Authorization", "Spring Security and JWT", "Unit and Integration Testing", "Redis and Caching", "Kafka and Asynchronous Messaging", "Microservices Fundamentals", "Docker and CI/CD", "Logging, Metrics and Tracing", "Cloud Deployment", "Backend System Design"),
            List.of("Java Coding Challenges", "SQL Exercises", "REST API Exercises", "Backend Debugging", "Authentication Lab", "System Design Cases"),
            List.of("Secure REST API", "Database-Backed Spring Boot Application", "Event-Driven Microservice", "Production Backend Capstone")); }

    private CareerDefinition software() { return definition(
            List.of("Programming Fundamentals", "Object-Oriented Programming", "Data Structures and Algorithms", "Computer Architecture Fundamentals", "Operating Systems", "Computer Networks", "Database Fundamentals", "Git and Collaborative Development", "Software Design Principles", "Design Patterns", "Testing and Debugging", "Concurrency Fundamentals", "Software Architecture", "Distributed Systems Fundamentals", "Security Fundamentals", "System Design"),
            List.of("Programming Challenges", "Data Structures and Algorithms Practice", "Debugging and Refactoring", "Software Design Exercises", "System Design Cases"),
            List.of("Command-Line Application", "Database-Backed Application", "Software Design Project", "Software Engineering Capstone")); }

    private CareerDefinition aiEngineer() { return definition(
            List.of("Python for AI", "AI and Machine Learning Essentials", "LLM Fundamentals", "Pre-Trained Models and Model APIs", "Prompt Engineering", "Context Engineering", "Embeddings", "Vector Databases", "Retrieval-Augmented Generation", "Tool and Function Calling", "AI Agents", "Evaluation and Guardrails", "Responsible AI and Security", "Observability, Cost and Deployment"),
            List.of("Prompt Experiments", "Model API Labs", "RAG Exercises", "Evaluation Exercises", "Agent Workflow Challenges"),
            List.of("AI Assistant", "Grounded RAG Application", "Agentic Workflow", "Production AI Capstone")); }

    private CareerDefinition machineLearning() { return definition(
            List.of("Python for Machine Learning", "Linear Algebra", "Calculus", "Probability and Statistics", "Data Preparation", "Exploratory Data Analysis", "Supervised Learning", "Unsupervised Learning", "Feature Engineering", "Model Evaluation", "Deep Learning", "Experiment Tracking", "ML Pipelines", "Model Serving", "Monitoring and MLOps", "Responsible Machine Learning"),
            List.of("Notebook Exercises", "Feature Engineering Labs", "Model Evaluation", "Pipeline Debugging"),
            List.of("Prediction Project", "End-to-End ML Pipeline", "Deployed ML Capstone")); }

    private CareerDefinition dataAnalyst() { return definition(
            List.of("Data Literacy and Business Questions", "Excel and Spreadsheets", "SQL", "Statistics and Probability", "Data Cleaning", "Exploratory Data Analysis", "Python for Data Analysis", "Data Visualization", "Power BI", "Dashboard Design", "Business Metrics", "Data Storytelling"),
            List.of("Excel Assessments", "SQL Exercises", "Data Cleaning Labs", "Dashboard Critiques", "Business Cases"),
            List.of("Exploratory Analysis", "Business Dashboard", "Decision-Support Analytics Capstone")); }

    private CareerDefinition businessAnalyst() { return definition(
            List.of("Business Analysis Fundamentals", "Stakeholder Analysis", "Requirements Elicitation", "Process Modelling", "Data Analysis with Excel and SQL", "Agile and Product Delivery", "User Stories and Documentation", "Solution Evaluation", "Communication and Facilitation"),
            List.of("Requirement Workshops", "Process Mapping", "SQL Exercises", "Case Analysis"),
            List.of("Requirements Pack", "Process Improvement Study", "Business Analysis Capstone")); }

    private CareerDefinition dataEngineer() { return definition(
            List.of("Python and SQL", "Database Fundamentals", "Data Modeling", "ETL and ELT", "Data Warehousing", "Data Pipelines", "Distributed Data Processing", "Batch Processing", "Streaming Fundamentals", "Data Quality", "Cloud Data Platforms", "Workflow Orchestration", "Monitoring and Governance"),
            List.of("SQL Labs", "Pipeline Exercises", "Data Quality Checks", "Streaming Labs"),
            List.of("ETL Pipeline", "Data Warehouse", "Cloud Data Platform Capstone")); }

    private CareerDefinition cloudEngineer() { return definition(
            List.of("Linux and Networking", "Cloud Architecture Fundamentals", "Identity and Access Management", "Compute", "Storage", "Managed Databases", "Virtual Networks", "Containers", "Infrastructure as Code", "High Availability", "Disaster Recovery", "Monitoring", "Cost Management", "Cloud Security", "Automation and CI/CD"),
            List.of("Cloud Labs", "IAM Exercises", "Architecture Cases", "Cost and Security Reviews"),
            List.of("Secure Cloud Environment", "Highly Available Application", "Cloud Architecture Capstone")); }

    private CareerDefinition devOps() { return definition(
            List.of("Linux Administration", "Networking and DNS", "Shell and Python Automation", "Git", "Build and Release Fundamentals", "Docker and Containers", "CI/CD Pipelines", "Cloud Fundamentals", "Infrastructure as Code", "Kubernetes", "Configuration and Secrets", "Monitoring and Observability", "DevSecOps", "Reliability and Incident Response"),
            List.of("Linux Labs", "Pipeline Exercises", "Docker and Kubernetes Labs", "Incident Simulations"),
            List.of("CI/CD Pipeline", "Infrastructure as Code Environment", "Observable Cloud Platform Capstone")); }

    private CareerDefinition cyberSecurity() { return definition(
            List.of("Security, Ethics and Risk Fundamentals", "Networking and Protocols", "Linux and Windows Security", "Scripting for Security", "Identity and Access Management", "Cryptography Fundamentals", "Web and Application Security", "Network Security", "Vulnerability Management", "Security Monitoring and SIEM", "Threat Analysis", "Incident Response and Digital Forensics", "Cloud Security", "Governance, Risk and Compliance"),
            List.of("Security Labs", "Log Analysis", "Vulnerability Triage", "Incident Simulations"),
            List.of("Security Assessment", "Detection and Response Playbook", "SOC Analysis Capstone")); }

    private CareerDefinition qa() { return definition(
            List.of("Software Quality Fundamentals", "Requirements and Risk-Based Testing", "Test Planning and Design", "Manual Web and Mobile Testing", "API Testing", "SQL and Data Validation", "Test Automation", "UI Automation", "Performance Testing", "Security Testing Basics", "CI/CD Integration", "Defect Analysis and Quality Metrics"),
            List.of("Test Case Design", "API Test Labs", "Automation Exercises", "Defect Triage"),
            List.of("Test Strategy", "Automated Regression Suite", "Quality Engineering Capstone")); }

    private CareerDefinition technicalSupport() { return definition(
            List.of("Computer Hardware and Operating Systems", "Networking Fundamentals", "Windows and Linux Support", "Identity and Access Basics", "Ticketing and IT Service Management", "Troubleshooting Methodology", "Remote Support", "Security and Data Protection", "Customer Communication"),
            List.of("Troubleshooting Labs", "Ticket Triage", "Network Diagnostics", "Support Simulations"),
            List.of("Support Knowledge Base", "Incident Resolution Portfolio", "Technical Support Capstone")); }

    private CareerDefinition mechanicalDesign() { return definition(
            List.of("Engineering Mechanics", "Materials and Manufacturing", "Engineering Drawing", "CAD and Parametric Modelling", "Machine Elements", "Design Calculations", "Design for Manufacturing", "Simulation and Validation", "Standards, Tolerances and GD&T"),
            List.of("CAD Exercises", "Design Calculations", "Drawing Reviews", "Failure Analysis"),
            List.of("Component Design", "Assembly Design", "Validated Design Capstone")); }

    private CareerDefinition manufacturing() { return definition(
            List.of("Manufacturing Processes", "Production Planning", "Industrial Engineering", "Lean Manufacturing", "Quality Control", "Automation Fundamentals", "Maintenance and Reliability", "Operations Analytics", "Safety and Sustainability"),
            List.of("Process Mapping", "Capacity Exercises", "Quality Tools", "Root-Cause Analysis"),
            List.of("Line Balancing Study", "Process Improvement", "Manufacturing Capstone")); }

    private CareerDefinition engineeringQuality() { return definition(
            List.of("Quality Management Systems", "Measurement and Metrology", "Statistical Process Control", "Inspection and Testing", "Root-Cause Analysis", "FMEA", "Audits and Corrective Action", "Supplier Quality", "Safety and Documentation"),
            List.of("SPC Exercises", "FMEA Cases", "Audit Checklists", "Root-Cause Analysis"),
            List.of("Quality Improvement Study", "Inspection Plan", "Quality Engineering Capstone")); }

    private CareerDefinition maintenance() { return definition(
            List.of("Equipment and Systems Fundamentals", "Preventive and Predictive Maintenance", "Reliability Fundamentals", "Troubleshooting", "Condition Monitoring", "Maintenance Planning", "Spare Parts and Documentation", "Safety and Permit Systems"),
            List.of("Fault Diagnosis", "Maintenance Planning", "Reliability Cases", "Safety Exercises"),
            List.of("Preventive Maintenance Program", "Reliability Improvement", "Maintenance Capstone")); }

    private CareerDefinition engineeringOperations() { return definition(
            List.of("Engineering Operations", "Process Mapping", "Capacity and Resource Planning", "Industrial Safety", "Quality Management", "Maintenance Coordination", "Lean and Continuous Improvement", "Operations Analytics"),
            List.of("Operations Cases", "Process Mapping", "Quality Tools", "Capacity Exercises"),
            List.of("Operations Improvement", "Engineering Dashboard", "Operations Capstone")); }

    private CareerDefinition siteEngineering() { return definition(
            List.of("Construction Materials", "Surveying", "Construction Methods", "Reading Drawings", "Quantity Estimation", "Planning and Scheduling", "Site Safety", "Quality Control", "Contracts and Documentation"),
            List.of("Estimation", "Drawing Interpretation", "Schedule Exercises", "Site Cases"),
            List.of("Building Execution Plan", "Quantity and Schedule Project", "Site Management Capstone")); }

    private CareerDefinition structuralEngineering() { return definition(
            List.of("Engineering Mechanics", "Structural Analysis", "Concrete Structures", "Steel Structures", "Foundation Engineering", "Structural Software", "Codes and Loads", "Detailing", "Resilience and Sustainability"),
            List.of("Analysis Problems", "Design Checks", "Detailing", "Code-Based Cases"),
            List.of("RC Structure Design", "Steel Structure Design", "Structural Capstone")); }

    private CareerDefinition quantitySurveying() { return definition(
            List.of("Construction Drawings", "Quantity Take-Off", "Estimation and Costing", "Rate Analysis", "Measurement Standards", "Tendering and Contracts", "Billing", "Cost Control", "BIM for Quantity Surveying"),
            List.of("Quantity Exercises", "Rate Analysis", "Tender Cases", "Billing Practice"),
            List.of("Bill of Quantities", "Cost Plan", "Quantity Surveying Capstone")); }

    private CareerDefinition civilPlanning() { return definition(
            List.of("Surveying and Geomatics", "Construction Planning", "Scheduling", "Resource Planning", "Cost and Progress Control", "GIS and Mapping", "Contracts and Documentation", "Safety and Quality"),
            List.of("Survey Exercises", "Schedule Development", "Progress Analysis", "Planning Cases"),
            List.of("Construction Schedule", "Survey and Planning Study", "Planning Capstone")); }

    private CareerDefinition environmentalEngineering() { return definition(
            List.of("Environmental Science", "Water and Wastewater", "Solid Waste Management", "Air Pollution Control", "Environmental Impact Assessment", "Environmental Regulations", "Monitoring and Data Analysis", "Sustainability"),
            List.of("Impact Assessment Cases", "Monitoring Exercises", "Treatment Calculations", "Compliance Review"),
            List.of("Environmental Assessment", "Treatment Design Study", "Environmental Capstone")); }

    private CareerDefinition electricalEngineering() { return definition(
            List.of("Circuit Analysis", "Electrical Machines", "Power Systems", "Protection and Switchgear", "Electrical Design and Drafting", "Control Fundamentals", "Instrumentation", "Standards and Safety", "Energy Efficiency"),
            List.of("Circuit Problems", "Load Calculations", "Protection Cases", "Drawing Reviews"),
            List.of("Electrical Distribution Design", "Protection Study", "Electrical Engineering Capstone")); }

    private CareerDefinition embeddedSystems() { return definition(
            List.of("Digital Electronics", "C and Embedded C", "Microcontrollers", "Computer Architecture", "Sensors and Interfaces", "Real-Time Systems", "Communication Protocols", "PCB Fundamentals", "Hardware Debugging", "Embedded Security and IoT"),
            List.of("Firmware Exercises", "Interface Labs", "Protocol Debugging", "Hardware Testing"),
            List.of("Sensor Device", "Connected Embedded System", "Embedded Product Capstone")); }

    private CareerDefinition vlsi() { return definition(
            List.of("Digital Logic", "Semiconductor Fundamentals", "CMOS Design", "HDL with Verilog or VHDL", "RTL Design", "Functional Verification", "Synthesis and Timing", "Physical Design Basics", "Low-Power Design", "DFT Fundamentals"),
            List.of("HDL Exercises", "Simulation Labs", "Timing Analysis", "Verification Cases"),
            List.of("RTL Module", "Verified Digital System", "VLSI Capstone")); }

    private CareerDefinition telecom() { return definition(
            List.of("Communication Systems", "Signals and Systems", "Analog and Digital Communication", "Wireless Fundamentals", "RF Fundamentals", "Networking", "Optical Communication", "Mobile Networks", "Telecom Testing and Optimization"),
            List.of("Signal Problems", "Link Budget Exercises", "Network Cases", "Testing Labs"),
            List.of("Communication Link Study", "Network Optimization", "Telecom Capstone")); }

    private CareerDefinition chemicalProcess() { return definition(
            List.of("Material and Energy Balances", "Thermodynamics", "Fluid Flow", "Heat Transfer", "Mass Transfer", "Reaction Engineering", "Separation Processes", "Process Control", "Process Safety", "Simulation and Optimization"),
            List.of("Process Calculations", "Simulation Labs", "HAZOP Cases", "Optimization Exercises"),
            List.of("Process Design", "Plant Optimization", "Safety and Sustainability Capstone")); }

    private CareerDefinition automotive() { return definition(
            List.of("Vehicle Systems", "Engineering Mechanics", "Automotive Materials", "CAD and CAE", "Powertrain Fundamentals", "Vehicle Dynamics", "Manufacturing and Quality", "Diagnostics", "EV Architecture and Safety"),
            List.of("Design Calculations", "CAD Exercises", "Diagnostics", "Validation Cases"),
            List.of("Subsystem Design", "EV Architecture Study", "Automotive Capstone")); }

    private CareerDefinition biotechEngineering() { return definition(
            List.of("Biology and Biochemistry", "Bioprocess Fundamentals", "Cell and Molecular Biology", "Laboratory Methods", "Bioreactors", "Downstream Processing", "Biostatistics", "Quality Systems", "Regulatory Fundamentals", "Biomedical Instrumentation"),
            List.of("Laboratory Cases", "Bioprocess Calculations", "Quality Documentation", "Data Interpretation"),
            List.of("Bioprocess Study", "Biomedical Design Study", "Life Sciences Capstone")); }

    private CareerDefinition accounting() { return definition(
            List.of("Accounting Principles", "Bookkeeping and Ledgers", "Financial Statements", "Cost and Management Accounting", "Tax Fundamentals", "Audit Fundamentals", "Corporate and Business Law", "Excel and Accounting Systems", "Controls and Compliance"),
            List.of("Journal and Ledger Practice", "Reconciliation", "Tax Cases", "Audit Working Papers"),
            List.of("Accounts Closing Project", "Tax and Compliance Case", "Accounting Capstone")); }

    private CareerDefinition finance() { return definition(
            List.of("Financial Accounting", "Financial Statement Analysis", "Corporate Finance", "Economics and Markets", "Advanced Excel", "Financial Modelling", "Valuation", "Investment Analysis", "Credit Analysis", "Risk and Scenario Analysis", "Presentation of Financial Insights"),
            List.of("Statement Analysis", "Excel Modelling", "Valuation Exercises", "Investment Cases"),
            List.of("Company Analysis", "Valuation Model", "Investment Recommendation Capstone")); }

    private CareerDefinition risk() { return definition(
            List.of("Risk Management Fundamentals", "Financial and Operational Risk", "Credit Risk", "Market Risk", "Compliance Risk", "Risk Measurement", "Controls", "Scenario and Stress Testing", "Risk Reporting", "Governance"),
            List.of("Risk Identification", "Control Mapping", "Scenario Analysis", "Risk Reporting Cases"),
            List.of("Risk Register", "Control Assessment", "Enterprise Risk Capstone")); }

    private CareerDefinition hrGeneralist() { return definition(
            List.of("HR and Organizational Behaviour", "Workforce Planning", "Talent Acquisition", "Onboarding", "Learning and Development", "Performance Management", "Compensation and Benefits", "Employee Relations", "HR Policies and Employment Compliance", "HR Analytics and HRIS"),
            List.of("Interview Simulations", "Policy Cases", "People Analytics", "Employee Relations Scenarios"),
            List.of("Workforce Plan", "HR Policy Portfolio", "Employee Experience Capstone")); }

    private CareerDefinition talentAcquisition() { return definition(
            List.of("Workforce and Hiring Planning", "Job Analysis", "Sourcing", "Screening", "Structured Interviews", "Selection Assessment", "Candidate Experience", "Offer Management", "Recruitment Metrics", "Employment Compliance"),
            List.of("Sourcing Exercises", "Resume Screening", "Interview Simulations", "Recruitment Analytics"),
            List.of("Recruitment Strategy", "Interview Toolkit", "Talent Acquisition Capstone")); }

    private CareerDefinition learningDevelopment() { return definition(
            List.of("Adult Learning", "Learning Needs Analysis", "Instructional Design", "Content Development", "Facilitation", "Learning Technology", "Assessment", "Program Evaluation", "Learning Analytics"),
            List.of("Needs Analysis", "Lesson Design", "Facilitation Practice", "Evaluation Exercises"),
            List.of("Learning Program", "Assessment Plan", "L&D Capstone")); }

    private CareerDefinition employeeRelations() { return definition(
            List.of("Employee Relations", "Employment Policies", "Performance and Conduct", "Grievance Handling", "Compensation Fundamentals", "Benefits", "Workplace Investigation", "Compliance", "People Analytics"),
            List.of("Policy Review", "Employee Cases", "Compensation Exercises", "Investigation Scenarios"),
            List.of("Employee Relations Playbook", "Compensation Analysis", "HR Operations Capstone")); }

    private CareerDefinition marketingAnalytics() { return definition(
            List.of("Marketing Fundamentals", "Consumer Behaviour", "Market Research", "Segmentation and Positioning", "Marketing Metrics", "Excel and SQL", "Visualization", "Experimentation", "Attribution", "Campaign Reporting"),
            List.of("Research Exercises", "Campaign Analysis", "Metrics Cases", "Dashboard Practice"),
            List.of("Market Study", "Campaign Dashboard", "Marketing Analytics Capstone")); }

    private CareerDefinition digitalMarketing() { return definition(
            List.of("Digital Marketing Fundamentals", "Audience and Customer Journeys", "Content Marketing", "SEO", "Paid Search", "Social Media", "Email and Lifecycle Marketing", "Conversion Optimization", "Analytics and Attribution", "Campaign Planning"),
            List.of("SEO Audits", "Campaign Exercises", "Content Planning", "Analytics Review"),
            List.of("Digital Campaign", "Channel Strategy", "Digital Marketing Capstone")); }

    private CareerDefinition brandMarketing() { return definition(
            List.of("Marketing and Consumer Behaviour", "Market Research", "Segmentation", "Positioning", "Brand Strategy", "Messaging", "Go-to-Market Planning", "Product Marketing", "Campaign Management", "Brand Measurement"),
            List.of("Positioning Exercises", "Messaging Practice", "Campaign Cases", "Brand Reviews"),
            List.of("Brand Strategy", "Go-to-Market Plan", "Brand Marketing Capstone")); }

    private CareerDefinition salesBusinessDevelopment() { return definition(
            List.of("Sales Fundamentals", "Customer and Market Research", "Prospecting", "Consultative Selling", "Pipeline Management", "Negotiation", "Account Management", "CRM", "Sales Metrics", "Business Development Strategy"),
            List.of("Sales Simulations", "Prospecting Exercises", "Negotiation Cases", "Pipeline Reviews"),
            List.of("Sales Strategy", "Account Plan", "Business Development Capstone")); }

    private CareerDefinition businessOperations() { return definition(
            List.of("Operations Fundamentals", "Process Mapping", "Capacity and Resource Planning", "Inventory", "Quality Management", "Project Management", "Excel and Operations Analytics", "Continuous Improvement", "Risk and Controls"),
            List.of("Process Cases", "Capacity Exercises", "Quality Tools", "Operations Dashboards"),
            List.of("Process Improvement", "Operations Dashboard", "Business Operations Capstone")); }

    private CareerDefinition supplyChain() { return definition(
            List.of("Supply Chain Fundamentals", "Demand Planning", "Inventory", "Procurement", "Supplier Management", "Logistics", "Warehousing", "Global Sourcing", "Supply Chain Analytics", "Risk and Sustainability"),
            List.of("Inventory Exercises", "Procurement Cases", "Network Analysis", "Supplier Evaluation"),
            List.of("Supply Chain Study", "Procurement Plan", "Supply Chain Capstone")); }

    private CareerDefinition internationalBusiness() { return definition(
            List.of("International Trade", "Global Markets", "Export Documentation", "Trade Finance", "Foreign Exchange", "Global Strategy", "Cross-Cultural Management", "International Logistics", "Market Entry", "Trade Compliance"),
            List.of("Trade Cases", "Market Entry Analysis", "Export Documentation", "Negotiation Simulation"),
            List.of("International Market Entry", "Export Plan", "Global Business Capstone")); }

    private CareerDefinition entrepreneurship() { return definition(
            List.of("Opportunity Discovery", "Customer Discovery", "Business Model Design", "Market Validation", "Product Strategy", "Startup Finance", "Sales and Growth", "Operations", "Pitching", "Innovation Management"),
            List.of("Customer Interviews", "Business Model Cases", "Pitch Exercises", "Startup Finance Exercises"),
            List.of("Validated Business Model", "Investor Pitch", "Startup Capstone")); }

    private CareerDefinition compliance() { return definition(
            List.of("Regulatory and Business Fundamentals", "Governance, Ethics and Compliance Frameworks", "Policies and Procedures", "Risk Assessment", "Internal Controls", "Regulatory Research", "Monitoring and Testing", "Issue and Incident Management", "Audit Support", "Documentation and Reporting", "Data Privacy and Domain Regulation"),
            List.of("Control Mapping", "Policy Review", "Compliance Testing", "Incident and Reporting Cases"),
            List.of("Compliance Gap Assessment", "Controls Testing Portfolio", "Compliance Program Capstone")); }

    private CareerDefinition contentStrategy() { return definition(
            List.of("Audience and Market Research", "Content Strategy", "Brand Voice and Messaging", "Content Architecture", "Editorial Planning", "Writing and Editing", "SEO and Discoverability", "Distribution and Channels", "CMS and Content Operations", "Analytics and Optimization", "Content Governance"),
            List.of("Content Audits", "Editorial Planning", "SEO Briefs", "Analytics Reviews"),
            List.of("Content Strategy", "Editorial and Distribution Plan", "Content Portfolio Capstone")); }

    private CareerDefinition journalismWriting() { return definition(
            List.of("News and Feature Writing", "Research and Verification", "Interviewing", "Editing", "Media Ethics", "Digital Publishing", "Multimedia Storytelling", "Audience and Distribution", "Portfolio Development"),
            List.of("Writing Tests", "Interview Exercises", "Editing Practice", "Fact-Checking Cases"),
            List.of("Published Story Portfolio", "Multimedia Feature", "Writing Capstone")); }

    private CareerDefinition communicationsPr() { return definition(
            List.of("Communication Strategy", "Audience Analysis", "Message Development", "Media Relations", "Public Relations", "Internal Communication", "Crisis Communication", "Digital Channels", "Campaign Measurement"),
            List.of("Press Release Writing", "Media Pitching", "Crisis Scenarios", "Campaign Planning"),
            List.of("Communication Plan", "PR Campaign", "Communications Capstone")); }

    private CareerDefinition policyResearch() { return definition(
            List.of("Public Policy Fundamentals", "Research Methods", "Policy Analysis", "Economics and Data", "Stakeholder Analysis", "Legal and Regulatory Research", "Impact Assessment", "Policy Writing", "Presentation and Advocacy"),
            List.of("Policy Cases", "Evidence Review", "Impact Analysis", "Policy Brief Writing"),
            List.of("Policy Brief", "Impact Study", "Policy Research Capstone")); }

    private CareerDefinition research() { return definition(
            List.of("Research Design", "Literature Review", "Qualitative Methods", "Quantitative Methods", "Data Collection", "Statistics and Analysis", "Research Ethics", "Academic Writing", "Reference Management", "Presentation and Publication"),
            List.of("Literature Reviews", "Method Critiques", "Data Analysis", "Research Proposals"),
            List.of("Research Proposal", "Analysis Report", "Publication-Ready Capstone")); }

    private CareerDefinition socialImpact() { return definition(
            List.of("Community Development", "Social Policy", "Needs Assessment", "Casework", "Program Design", "Program Management", "Monitoring and Evaluation", "Stakeholder Engagement", "CSR and Impact Reporting"),
            List.of("Community Cases", "Needs Assessment", "Report Writing", "Stakeholder Communication"),
            List.of("Community Needs Study", "CSR Project Plan", "Impact Evaluation")); }

    private CareerDefinition behaviouralResearch() { return definition(
            List.of("Psychological Foundations", "Research Methods", "Statistics", "Behavioural Measurement", "Observation and Interviewing", "Organizational Behaviour", "Consumer Behaviour", "Ethics", "Reporting"),
            List.of("Behavioural Cases", "Research Exercises", "Observation Reports", "Data Interpretation"),
            List.of("Behavioural Study", "Survey Research", "Behavioural Research Capstone")); }

    private CareerDefinition uxDesign() { return definition(
            List.of("Design Thinking", "User Research", "Information Architecture", "Interaction Design", "Visual Design", "Wireframing", "Prototyping", "Usability Testing", "Accessibility", "Design Systems", "Product Collaboration and Handoff"),
            List.of("Research Plans", "Design Challenges", "Usability Tests", "Critiques"),
            List.of("UX Case Study", "Product Prototype", "UX Portfolio Capstone")); }

    private CareerDefinition visualDesign() { return definition(
            List.of("Design Principles", "Typography", "Color", "Layout", "Brand Identity", "Illustration and Image Editing", "Digital Design Tools", "Motion Basics", "Portfolio Development"),
            List.of("Visual Exercises", "Brand Critiques", "Tool Practice", "Design Challenges"),
            List.of("Brand Identity", "Digital Campaign Assets", "Visual Design Portfolio")); }

    private CareerDefinition architecture() { return definition(
            List.of("Architectural Design", "Building Construction", "History and Theory", "Structures and Services", "CAD", "BIM", "Sustainable Design", "Codes and Professional Practice", "Visualization"),
            List.of("Design Studios", "Drawing Interpretation", "Modeling Exercises", "Portfolio Reviews"),
            List.of("Design Portfolio", "Sustainable Building Project", "Architecture Capstone")); }

    private CareerDefinition bim() { return definition(
            List.of("Building Information Modeling", "Revit Fundamentals", "Architectural and Structural Models", "MEP Coordination", "Families and Standards", "Clash Detection", "Quantity Extraction", "4D and 5D Basics", "Common Data Environments"),
            List.of("Modeling Exercises", "Coordination Cases", "Clash Reviews", "Quantity Extraction"),
            List.of("Coordinated BIM Model", "BIM Execution Plan", "BIM Capstone")); }

    private CareerDefinition urbanPlanning() { return definition(
            List.of("Urban Planning Fundamentals", "Land Use", "Housing", "Transport Planning", "Infrastructure Planning", "Environmental Planning", "Planning Law", "Survey and Community Engagement", "GIS", "Plan Preparation"),
            List.of("Survey Analysis", "Planning Cases", "Report Writing", "Plan Reviews"),
            List.of("Local Area Plan", "Transport or Housing Study", "Urban Planning Capstone")); }

    private CareerDefinition gis() { return definition(
            List.of("Spatial Data Fundamentals", "Coordinate Systems", "GIS Software", "Data Collection", "Spatial Analysis", "Remote Sensing Basics", "Cartography", "Web GIS", "GIS for Planning"),
            List.of("Mapping Exercises", "Spatial Analysis", "Data Cleaning", "GIS Cases"),
            List.of("Spatial Analysis Project", "Interactive Map", "GIS Capstone")); }

    private CareerDefinition regulatedQuality() { return definition(
            List.of("Quality Management Systems", "Good Documentation Practices", "Risk Management", "Deviation and CAPA", "Change Control", "Audit Readiness", "Validation Fundamentals", "Regulatory Compliance", "Data Integrity"),
            List.of("SOP Reviews", "CAPA Cases", "Audit Checklists", "Documentation Exercises"),
            List.of("Quality System Study", "CAPA Portfolio", "Audit Readiness Capstone")); }

    private CareerDefinition clinicalDataResearch() { return definition(
            List.of("Clinical Research Fundamentals", "Good Clinical Practice", "Medical Terminology", "Case Report Forms", "Clinical Data Standards", "Data Management Plans", "Data Cleaning and Queries", "Safety Data Basics", "Quality Control", "Privacy and Regulatory Compliance"),
            List.of("CRF Review", "Data Query Exercises", "Quality Checks", "Documentation Cases"),
            List.of("Clinical Data Plan", "Data Cleaning Portfolio", "Clinical Research Capstone")); }

    private CareerDefinition medicalSales() { return definition(
            List.of("Pharmacology and Product Knowledge", "Healthcare Markets", "Ethical Promotion", "Customer Segmentation", "Consultative Selling", "Territory Planning", "CRM", "Communication and Presentation", "Compliance"),
            List.of("Product Detailing", "Sales Simulations", "Territory Cases", "Compliance Scenarios"),
            List.of("Territory Plan", "Product Presentation", "Medical Sales Capstone")); }

    private CareerDefinition healthcareOperations() { return definition(
            List.of("Healthcare Delivery Systems", "Hospital Operations", "Patient Flow", "Quality and Patient Safety", "Healthcare Documentation", "Inventory and Support Services", "Healthcare Analytics", "Compliance and Accreditation", "Patient Experience"),
            List.of("Patient Flow Cases", "Quality Analysis", "Operations Dashboards", "Service Scenarios"),
            List.of("Process Improvement", "Patient Experience Study", "Hospital Operations Capstone")); }

    private CareerDefinition healthcareAnalytics() { return definition(
            List.of("Healthcare Systems", "Health Economics", "Healthcare Data", "Quality Measures", "Statistics", "Excel and BI", "Patient and Operations Analytics", "Privacy and Compliance", "Consulting and Communication"),
            List.of("Healthcare Data Interpretation", "Quality Analysis", "Dashboard Cases", "Consulting Cases"),
            List.of("Healthcare Dashboard", "Quality Improvement Study", "Healthcare Analytics Capstone")); }

    private CareerDefinition laboratoryScience() { return definition(
            List.of("Laboratory Fundamentals", "Microbiology or Domain Science", "Sample Handling", "Laboratory Methods", "Instrumentation", "Quality Control", "Biosafety", "Biostatistics", "Scientific Documentation", "Regulatory Requirements"),
            List.of("Laboratory Cases", "Quality Analysis", "Data Interpretation", "Documentation Exercises"),
            List.of("Laboratory Study", "Quality Control Project", "Laboratory Science Capstone")); }

    private CareerDefinition agriculture() { return definition(
            List.of("Crop Science", "Soil Science", "Water Management", "Plant Protection", "Farm Economics", "Agribusiness", "Agricultural Extension", "Supply Chain", "Digital Agriculture", "Sustainability"),
            List.of("Field Cases", "Farm Economics Exercises", "Quality Analysis", "Data Interpretation"),
            List.of("Farm Business Plan", "Crop Productivity Study", "Agriculture Capstone")); }

    private CareerDefinition hospitality() { return definition(
            List.of("Hospitality Operations", "Front Office", "Food and Beverage", "Housekeeping", "Guest Service", "Revenue Management", "Sales and Events", "Quality and Safety", "Hospitality Analytics"),
            List.of("Service Simulations", "Operations Cases", "Communication Scenarios", "Revenue Exercises"),
            List.of("Hotel Operations Plan", "Guest Experience Study", "Hospitality Capstone")); }

    private CareerDefinition tourism() { return definition(
            List.of("Tourism Operations", "Itinerary Planning", "Destination Management", "Travel Products and Distribution", "Travel Sales", "Customer Service", "Sustainable Tourism", "Digital Marketing", "Travel Regulations"),
            List.of("Itinerary Exercises", "Service Cases", "Sales Simulations", "Destination Analysis"),
            List.of("Tour Package Plan", "Destination Campaign", "Tourism Operations Capstone")); }

    private CareerDefinition education() { return definition(
            List.of("Learning Foundations", "Learner Analysis", "Curriculum Design", "Instructional Design", "Assessment", "Educational Technology", "Facilitation", "Learner Support", "Learning Analytics", "Quality and Ethics"),
            List.of("Lesson Planning", "Assessment Creation", "Presentation Practice", "Learner Cases"),
            List.of("Learning Module", "Assessment Plan", "Education Capstone")); }

    private CareerDefinition vocational() { return definition(
            List.of("Trade Fundamentals", "Technical Drawing", "Workshop Practice", "Tools and Measurement", "Safety", "Quality Basics", "Maintenance Practice", "Troubleshooting", "Workplace Communication"),
            List.of("Technical Aptitude", "Troubleshooting", "Workshop Exercises", "Safety Assessments"),
            List.of("Workshop Project", "Maintenance Case", "Vocational Capstone")); }

    private CareerDefinition customerSuccess() { return definition(
            List.of("Customer Success Fundamentals", "Product Knowledge", "Onboarding", "Customer Communication", "Support and Escalation", "Adoption and Engagement", "Renewal and Retention", "CRM", "Customer Health Metrics", "Business Reviews"),
            List.of("Customer Scenarios", "Onboarding Exercises", "Health Score Analysis", "Business Review Practice"),
            List.of("Onboarding Plan", "Customer Success Playbook", "Customer Success Capstone")); }

    private CareerDefinition definition(List<String> domain, List<String> practice, List<String> projects) {
        return new CareerDefinition(domain, practice, projects);
    }

    private boolean isCareer(String roadmapKey, String targetRole, String... careerKeys) {
        String normalizedRoadmapKey = normalize(roadmapKey);
        String normalizedTargetRole = normalize(targetRole);
        for (String careerKey : careerKeys) {
            String normalizedCareerKey = normalize(careerKey);
            if (normalizedRoadmapKey.equals(normalizedCareerKey)
                    || normalizedTargetRole.equals(normalizedCareerKey)) {
                return true;
            }
        }
        return false;
    }

    private boolean matches(String value, String... expectedValues) {
        String normalizedValue = normalize(value);
        if (normalizedValue.isBlank()) return false;
        for (String expected : expectedValues) {
            String normalizedExpected = normalize(expected);
            if (normalizedExpected.isBlank()) continue;
            if (normalizedValue.equals(normalizedExpected)) return true;
            if (normalizedExpected.length() >= 4 && normalizedValue.contains(normalizedExpected)) return true;
        }
        return false;
    }

    private boolean matchesEither(String first, String second, String... expectedValues) {
        return matches(first, expectedValues) || matches(second, expectedValues);
    }

    private String normalize(String value) {
        return clean(value)
                .toLowerCase(Locale.ROOT)
                .replace("&", " and ")
                .replace("_", "-")
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-+", "")
                .replaceAll("-+$", "");
    }

    private String clean(String value) {
        return value == null ? "" : value.trim();
    }
}
