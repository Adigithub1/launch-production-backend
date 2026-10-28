package com.launch.roadmapservice.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class TemplateGraphGenerator {
	public record NodeSeed(String key, String title, String phase, int x, int y) {
	}

	public record EdgeSeed(String source, String target) {
	}

	public record GraphSeed(List<NodeSeed> nodes, List<EdgeSeed> edges) {
	}

	public GraphSeed generate(String role) {
		String r = role == null ? "" : role.toLowerCase(Locale.ROOT);
		if (r.contains("ai") || r.contains("machine"))
			return ai();
		if (r.contains("data analyst") || r.contains("business analyst"))
			return data();
		if (r.contains("full stack"))
			return fullstack();
		if (r.contains("backend") || r.contains("java developer") || r.contains("spring"))
			return backend();
		if (r.contains("devops") || r.contains("cloud"))
			return devops();
		if (r.contains("cyber") || r.contains("security"))
			return cyber();
		if (r.contains("account") || r.contains("finance"))
			return accounting();
		if (r.contains("marketing"))
			return marketing();
		return software(role);
	}

	private GraphSeed ai() {
		return graph(
				new String[][] { { "python", "Python Fundamentals", "Foundation", "0", "0" },
						{ "git", "Git and Environments", "Foundation", "240", "0" },
						{ "math", "Linear Algebra", "Math", "-240", "170" },
						{ "stats", "Probability and Statistics", "Math", "0", "170" },
						{ "numpy", "NumPy", "Data", "240", "170" }, { "pandas", "Pandas", "Data", "480", "170" },
						{ "eda", "Data Cleaning and EDA", "Data", "240", "340" },
						{ "ml", "Machine Learning Foundations", "Machine Learning", "0", "510" },
						{ "regression", "Regression", "Machine Learning", "-300", "680" },
						{ "classification", "Classification", "Machine Learning", "0", "680" },
						{ "clustering", "Clustering", "Machine Learning", "300", "680" },
						{ "evaluation", "Model Evaluation", "Machine Learning", "0", "850" },
						{ "feature", "Feature Engineering", "Machine Learning", "300", "850" },
						{ "deep", "Deep Learning", "Deep Learning", "0", "1020" },
						{ "cnn", "Computer Vision", "Deep Learning", "-300", "1190" },
						{ "nlp", "NLP", "Deep Learning", "0", "1190" },
						{ "transformers", "Transformers", "Generative AI", "300", "1190" },
						{ "llm", "Large Language Models", "Generative AI", "0", "1360" },
						{ "prompt", "Prompt Engineering", "Generative AI", "-300", "1530" },
						{ "vectors", "Embeddings and Vector Databases", "Generative AI", "0", "1530" },
						{ "rag", "RAG Systems", "Generative AI", "300", "1530" },
						{ "agents", "AI Agents", "Generative AI", "0", "1700" },
						{ "api", "Model APIs and FastAPI", "Production", "-300", "1870" },
						{ "mlops", "MLOps and Monitoring", "Production", "0", "1870" },
						{ "responsible", "Responsible AI", "Production", "300", "1870" },
						{ "capstone", "AI Capstone", "Portfolio", "0", "2040" },
						{ "interview", "AI Interview Readiness", "Placement", "0", "2210" } },
				new String[][] { { "python", "math" }, { "python", "stats" }, { "python", "numpy" }, { "git", "numpy" },
						{ "numpy", "pandas" }, { "pandas", "eda" }, { "math", "ml" }, { "stats", "ml" },
						{ "eda", "ml" }, { "ml", "regression" }, { "ml", "classification" }, { "ml", "clustering" },
						{ "regression", "evaluation" }, { "classification", "evaluation" },
						{ "clustering", "evaluation" }, { "evaluation", "deep" }, { "feature", "deep" },
						{ "deep", "cnn" }, { "deep", "nlp" }, { "deep", "transformers" }, { "nlp", "llm" },
						{ "transformers", "llm" }, { "llm", "prompt" }, { "llm", "vectors" }, { "llm", "rag" },
						{ "vectors", "rag" }, { "rag", "agents" }, { "agents", "api" }, { "agents", "mlops" },
						{ "agents", "responsible" }, { "api", "capstone" }, { "mlops", "capstone" },
						{ "responsible", "capstone" }, { "capstone", "interview" } });
	}

	private GraphSeed data() {
		return graph(new String[][] { { "excel", "Excel Foundations", "Foundation", "0", "0" },
				{ "business", "Business Problem Framing", "Foundation", "260", "0" },
				{ "sql", "SQL Foundations", "Data", "0", "170" }, { "stats", "Statistics", "Data", "260", "170" },
				{ "cleaning", "Data Cleaning", "Analysis", "-260", "340" },
				{ "eda", "Exploratory Data Analysis", "Analysis", "0", "340" },
				{ "kpi", "KPIs and Metrics", "Analysis", "260", "340" },
				{ "viz", "Data Visualization Principles", "Visualization", "0", "510" },
				{ "powerbi", "Power BI", "Visualization", "-260", "680" },
				{ "tableau", "Tableau", "Visualization", "260", "680" },
				{ "dax", "DAX and Data Modeling", "BI", "-260", "850" },
				{ "story", "Data Storytelling", "BI", "260", "850" },
				{ "python", "Python for Analysis", "Advanced", "0", "1020" },
				{ "pandas", "Pandas", "Advanced", "-260", "1190" },
				{ "forecast", "Forecasting Basics", "Advanced", "260", "1190" },
				{ "dashboard", "Analytics Dashboard Project", "Portfolio", "0", "1360" },
				{ "case", "Business Case Study", "Portfolio", "0", "1530" },
				{ "interview", "Analyst Interview Readiness", "Placement", "0", "1700" } },
				new String[][] { { "excel", "sql" }, { "business", "stats" }, { "sql", "cleaning" }, { "sql", "eda" },
						{ "stats", "eda" }, { "business", "kpi" }, { "cleaning", "viz" }, { "eda", "viz" },
						{ "kpi", "viz" }, { "viz", "powerbi" }, { "viz", "tableau" }, { "powerbi", "dax" },
						{ "tableau", "story" }, { "dax", "python" }, { "story", "python" }, { "python", "pandas" },
						{ "python", "forecast" }, { "pandas", "dashboard" }, { "forecast", "dashboard" },
						{ "dashboard", "case" }, { "case", "interview" } });
	}

	private GraphSeed backend() {
		return graph(new String[][] { { "internet", "Internet, DNS and HTTP", "Foundation", "0", "0" },
				{ "java", "Core Java", "Language", "-250", "170" }, { "git", "Git and GitHub", "Tools", "250", "170" },
				{ "oop", "OOP and Design Principles", "Language", "-250", "340" },
				{ "dsa", "Data Structures and Algorithms", "Language", "250", "340" },
				{ "sql", "SQL", "Database", "-300", "510" }, { "postgres", "PostgreSQL", "Database", "0", "510" },
				{ "nosql", "NoSQL Basics", "Database", "300", "510" },
				{ "spring", "Spring Boot", "Framework", "0", "680" },
				{ "rest", "REST API Design", "API", "-260", "850" },
				{ "validation", "Validation and Error Handling", "API", "260", "850" },
				{ "security", "Spring Security and JWT", "Security", "-260", "1020" },
				{ "testing", "JUnit and Integration Testing", "Quality", "260", "1020" },
				{ "cache", "Redis and Caching", "Scale", "-300", "1190" },
				{ "kafka", "Kafka and Messaging", "Scale", "0", "1190" },
				{ "microservices", "Microservices", "Scale", "300", "1190" },
				{ "docker", "Docker", "Delivery", "-260", "1360" }, { "cicd", "CI/CD", "Delivery", "260", "1360" },
				{ "observability", "Logs Metrics Traces", "Operations", "0", "1530" },
				{ "cloud", "Cloud Deployment", "Operations", "0", "1700" },
				{ "project", "Backend Capstone", "Portfolio", "0", "1870" },
				{ "interview", "Backend Interview Readiness", "Placement", "0", "2040" } },
				new String[][] { { "internet", "java" }, { "internet", "git" }, { "java", "oop" }, { "java", "dsa" },
						{ "oop", "sql" }, { "dsa", "postgres" }, { "dsa", "nosql" }, { "sql", "spring" },
						{ "postgres", "spring" }, { "spring", "rest" }, { "spring", "validation" },
						{ "rest", "security" }, { "validation", "testing" }, { "security", "cache" },
						{ "testing", "kafka" }, { "testing", "microservices" }, { "cache", "docker" },
						{ "kafka", "docker" }, { "microservices", "cicd" }, { "docker", "observability" },
						{ "cicd", "observability" }, { "observability", "cloud" }, { "cloud", "project" },
						{ "project", "interview" } });
	}

	private GraphSeed fullstack() {
		return graph(new String[][] { { "internet", "Internet and HTTP", "Foundation", "0", "0" },
				{ "git", "Git and GitHub", "Foundation", "260", "0" },
				{ "html", "Semantic HTML", "Frontend", "-260", "170" },
				{ "css", "CSS and Responsive Design", "Frontend", "0", "170" },
				{ "js", "JavaScript", "Frontend", "260", "170" },
				{ "typescript", "TypeScript", "Frontend", "0", "340" }, { "react", "React", "Frontend", "-260", "510" },
				{ "state", "State and Data Fetching", "Frontend", "260", "510" },
				{ "testingfe", "Frontend Testing", "Frontend", "0", "680" },
				{ "java", "Java", "Backend", "-260", "850" }, { "spring", "Spring Boot", "Backend", "260", "850" },
				{ "rest", "REST APIs", "Backend", "0", "1020" },
				{ "sql", "SQL and PostgreSQL", "Data", "-260", "1190" },
				{ "auth", "Authentication and JWT", "Security", "260", "1190" },
				{ "integration", "Frontend Backend Integration", "Application", "0", "1360" },
				{ "docker", "Docker", "Delivery", "-260", "1530" },
				{ "cicd", "CI/CD and Deployment", "Delivery", "260", "1530" },
				{ "project", "Full Stack Capstone", "Portfolio", "0", "1700" },
				{ "interview", "Full Stack Interview Readiness", "Placement", "0", "1870" } },
				new String[][] { { "internet", "html" }, { "internet", "css" }, { "git", "js" },
						{ "html", "typescript" }, { "css", "typescript" }, { "js", "typescript" },
						{ "typescript", "react" }, { "typescript", "state" }, { "react", "testingfe" },
						{ "state", "testingfe" }, { "testingfe", "java" }, { "testingfe", "spring" },
						{ "java", "rest" }, { "spring", "rest" }, { "rest", "sql" }, { "rest", "auth" },
						{ "sql", "integration" }, { "auth", "integration" }, { "integration", "docker" },
						{ "integration", "cicd" }, { "docker", "project" }, { "cicd", "project" },
						{ "project", "interview" } });
	}

	private GraphSeed devops() {
		return graph(new String[][] { { "linux", "Linux", "Foundation", "0", "0" },
				{ "network", "Networking", "Foundation", "260", "0" }, { "git", "Git", "Tools", "-260", "170" },
				{ "shell", "Shell Scripting", "Tools", "0", "170" },
				{ "python", "Python Automation", "Tools", "260", "170" },
				{ "docker", "Docker", "Containers", "0", "340" }, { "cicd", "CI/CD", "Delivery", "-260", "510" },
				{ "registry", "Artifact Registries", "Delivery", "260", "510" },
				{ "k8s", "Kubernetes", "Orchestration", "0", "680" },
				{ "iac", "Terraform", "Infrastructure", "-260", "850" },
				{ "cloud", "Cloud Fundamentals", "Infrastructure", "260", "850" },
				{ "config", "Configuration Management", "Infrastructure", "0", "1020" },
				{ "observability", "Observability", "Operations", "-260", "1190" },
				{ "sre", "SRE and Reliability", "Operations", "260", "1190" },
				{ "security", "DevSecOps", "Security", "0", "1360" },
				{ "project", "Platform Project", "Portfolio", "0", "1530" },
				{ "interview", "DevOps Interview Readiness", "Placement", "0", "1700" } },
				new String[][] { { "linux", "git" }, { "linux", "shell" }, { "network", "python" }, { "git", "docker" },
						{ "shell", "docker" }, { "python", "docker" }, { "docker", "cicd" }, { "docker", "registry" },
						{ "cicd", "k8s" }, { "registry", "k8s" }, { "k8s", "iac" }, { "k8s", "cloud" },
						{ "iac", "config" }, { "cloud", "config" }, { "config", "observability" }, { "config", "sre" },
						{ "observability", "security" }, { "sre", "security" }, { "security", "project" },
						{ "project", "interview" } });
	}

	private GraphSeed cyber() {
		return graph(new String[][] { { "computer", "Computer Fundamentals", "Foundation", "0", "0" },
				{ "network", "Networking", "Foundation", "260", "0" }, { "linux", "Linux", "Systems", "-260", "170" },
				{ "windows", "Windows Administration", "Systems", "260", "170" },
				{ "security", "Security Fundamentals", "Security", "0", "340" },
				{ "iam", "Identity and Access Management", "Security", "-260", "510" },
				{ "crypto", "Cryptography Basics", "Security", "260", "510" },
				{ "web", "Web Security", "Application Security", "-260", "680" },
				{ "networksec", "Network Security", "Infrastructure Security", "260", "680" },
				{ "siem", "SIEM and Log Analysis", "Operations", "-260", "850" },
				{ "incident", "Incident Response", "Operations", "260", "850" },
				{ "vuln", "Vulnerability Management", "Operations", "0", "1020" },
				{ "cloudsec", "Cloud Security", "Advanced", "-260", "1190" },
				{ "governance", "Risk and Compliance", "Advanced", "260", "1190" },
				{ "lab", "Security Lab Project", "Portfolio", "0", "1360" },
				{ "interview", "Security Interview Readiness", "Placement", "0", "1530" } },
				new String[][] { { "computer", "linux" }, { "network", "windows" }, { "linux", "security" },
						{ "windows", "security" }, { "security", "iam" }, { "security", "crypto" }, { "iam", "web" },
						{ "crypto", "networksec" }, { "web", "siem" }, { "networksec", "incident" }, { "siem", "vuln" },
						{ "incident", "vuln" }, { "vuln", "cloudsec" }, { "vuln", "governance" }, { "cloudsec", "lab" },
						{ "governance", "lab" }, { "lab", "interview" } });
	}

	private GraphSeed accounting() {
		return graph(
				new String[][] { { "principles", "Accounting Principles", "Foundation", "0", "0" },
						{ "excel", "Excel for Accounting", "Foundation", "260", "0" },
						{ "journal", "Journal and Ledger", "Core", "-260", "170" },
						{ "trial", "Trial Balance", "Core", "0", "170" },
						{ "statements", "Financial Statements", "Core", "260", "170" },
						{ "cost", "Cost Accounting", "Management Accounting", "-260", "340" },
						{ "budget", "Budgeting", "Management Accounting", "260", "340" },
						{ "tax", "Taxation Fundamentals", "Compliance", "-260", "510" },
						{ "audit", "Audit Fundamentals", "Compliance", "260", "510" },
						{ "erp", "ERP and Accounting Software", "Tools", "0", "680" },
						{ "analysis", "Financial Analysis", "Analysis", "-260", "850" },
						{ "reporting", "Management Reporting", "Analysis", "260", "850" },
						{ "case", "Accounting Case Project", "Portfolio", "0", "1020" },
						{ "interview", "Accounting Interview Readiness", "Placement", "0", "1190" } },
				new String[][] { { "principles", "journal" }, { "principles", "trial" }, { "excel", "statements" },
						{ "journal", "cost" }, { "trial", "budget" }, { "statements", "tax" },
						{ "statements", "audit" }, { "cost", "erp" }, { "budget", "erp" }, { "tax", "analysis" },
						{ "audit", "reporting" }, { "erp", "analysis" }, { "erp", "reporting" }, { "analysis", "case" },
						{ "reporting", "case" }, { "case", "interview" } });
	}

	private GraphSeed marketing() {
		return graph(
				new String[][] { { "market", "Marketing Fundamentals", "Foundation", "0", "0" },
						{ "research", "Market Research", "Foundation", "260", "0" },
						{ "customer", "Customer Segmentation", "Strategy", "-260", "170" },
						{ "position", "Positioning and Value Proposition", "Strategy", "260", "170" },
						{ "brand", "Brand Management", "Strategy", "0", "340" },
						{ "content", "Content Marketing", "Digital", "-300", "510" },
						{ "social", "Social Media Marketing", "Digital", "0", "510" },
						{ "seo", "SEO and SEM", "Digital", "300", "510" },
						{ "email", "Email and Lifecycle Marketing", "Channels", "-260", "680" },
						{ "performance", "Performance Marketing", "Channels", "260", "680" },
						{ "analytics", "Marketing Analytics", "Measurement", "0", "850" },
						{ "crm", "CRM and Automation", "Operations", "-260", "1020" },
						{ "budget", "Campaign Planning and Budgeting", "Operations", "260", "1020" },
						{ "campaign", "Integrated Campaign Project", "Portfolio", "0", "1190" },
						{ "interview", "Marketing Interview Readiness", "Placement", "0", "1360" } },
				new String[][] { { "market", "customer" }, { "research", "position" }, { "customer", "brand" },
						{ "position", "brand" }, { "brand", "content" }, { "brand", "social" }, { "brand", "seo" },
						{ "content", "email" }, { "social", "performance" }, { "seo", "performance" },
						{ "email", "analytics" }, { "performance", "analytics" }, { "analytics", "crm" },
						{ "analytics", "budget" }, { "crm", "campaign" }, { "budget", "campaign" },
						{ "campaign", "interview" } });
	}

	private GraphSeed software(String role) {

	    String safeRole = isBlank(role)
	            ? "Career"
	            : role.trim();

	    return generate(
	            slug(safeRole),
	            safeRole,
	            List.of(
	                    safeRole + " Fundamentals",
	                    "Industry Knowledge",
	                    "Professional Practices"
	            ),
	            List.of(
	                    "Applied Problem Solving",
	                    "Industry Case Studies",
	                    "Professional Assessment"
	            ),
	            List.of(
	                    safeRole + " Capstone Project"
	            )
	    );
	}
	private void addDynamicNode(
	        List<NodeSeed> graphNodes,
	        Set<String> usedKeys,
	        String requestedKey,
	        String title,
	        String phase,
	        int x,
	        int y) {

	    String key = createUniqueKey(
	            requestedKey,
	            usedKeys);

	    graphNodes.add(
	            new NodeSeed(
	                    key,
	                    title,
	                    phase,
	                    x,
	                    y));

	    usedKeys.add(key);
	}

	private String createUniqueKey(
	        String requestedKey,
	        Set<String> usedKeys) {

	    String base = isBlank(requestedKey)
	            ? "topic"
	            : requestedKey;

	    String candidate = base;
	    int counter = 2;

	    while (usedKeys.contains(candidate)) {
	        candidate = base + "-" + counter;
	        counter++;
	    }

	    return candidate;
	}

	private List<String> safeList(List<String> values) {

	    if (values == null) {
	        return List.of();
	    }

	    return values;
	}

	private String cleanRole(
	        String targetRole,
	        String roadmapKey) {

	    if (!isBlank(targetRole)) {
	        return targetRole.trim();
	    }

	    if (!isBlank(roadmapKey)) {

	        String converted = roadmapKey
	                .replace("-", " ")
	                .replace("_", " ")
	                .trim();

	        if (!converted.isBlank()) {
	            return titleCase(converted);
	        }
	    }

	    return "Career";
	}

	private String slug(String value) {

	    if (isBlank(value)) {
	        return "topic";
	    }

	    String result = value
	            .trim()
	            .toLowerCase(Locale.ROOT)
	            .replace("&", " and ")
	            .replaceAll("[^a-z0-9]+", "-")
	            .replaceAll("^-+", "")
	            .replaceAll("-+$", "");

	    return result.isBlank()
	            ? "topic"
	            : result;
	}

	private String titleCase(String value) {

	    String[] words = value.split("\\s+");
	    StringBuilder output = new StringBuilder();

	    for (String word : words) {

	        if (word.isBlank()) {
	            continue;
	        }

	        if (!output.isEmpty()) {
	            output.append(" ");
	        }

	        output.append(
	                Character.toUpperCase(
	                        word.charAt(0)));

	        if (word.length() > 1) {
	            output.append(
	                    word.substring(1));
	        }
	    }

	    return output.toString();
	}

	private boolean isBlank(String value) {
	    return value == null || value.isBlank();
	}
	
	private GraphSeed graph(String[][] ns, String[][] es) {
		List<NodeSeed> n = new ArrayList<>();
		for (String[] x : ns)
			n.add(new NodeSeed(x[0], x[1], x[2], Integer.parseInt(x[3]), Integer.parseInt(x[4])));
		List<EdgeSeed> e = new ArrayList<>();
		for (String[] x : es)
			e.add(new EdgeSeed(x[0], x[1]));
		return new GraphSeed(n, e);
	}
	public GraphSeed generate(
	        String roadmapKey,
	        String targetRole,
	        List<String> domain,
	        List<String> practice,
	        List<String> projects) {

	    String role = cleanRole(targetRole, roadmapKey);

	    List<NodeSeed> graphNodes = new ArrayList<>();
	    List<EdgeSeed> graphEdges = new ArrayList<>();
	    Set<String> usedKeys = new HashSet<>();

	    int y = 0;

	    // =========================================================
	    // COMMON PLACEMENT FOUNDATION
	    // =========================================================

	    addDynamicNode(
	            graphNodes,
	            usedKeys,
	            "quantitative-aptitude",
	            "Quantitative Aptitude",
	            "Placement Foundation",
	            -300,
	            y);

	    addDynamicNode(
	            graphNodes,
	            usedKeys,
	            "logical-reasoning",
	            "Logical Reasoning",
	            "Placement Foundation",
	            0,
	            y);

	    addDynamicNode(
	            graphNodes,
	            usedKeys,
	            "verbal-ability",
	            "Verbal Ability",
	            "Placement Foundation",
	            300,
	            y);

	    y += 180;

	    addDynamicNode(
	            graphNodes,
	            usedKeys,
	            "communication",
	            "Communication Skills",
	            "Placement Foundation",
	            0,
	            y);

	    graphEdges.add(
	            new EdgeSeed(
	                    "quantitative-aptitude",
	                    "communication"));

	    graphEdges.add(
	            new EdgeSeed(
	                    "logical-reasoning",
	                    "communication"));

	    graphEdges.add(
	            new EdgeSeed(
	                    "verbal-ability",
	                    "communication"));

	    // =========================================================
	    // CAREER FOUNDATION
	    // =========================================================

	    y += 180;

	    addDynamicNode(
	            graphNodes,
	            usedKeys,
	            "career-foundation",
	            role + " Foundations",
	            "Career Foundation",
	            0,
	            y);

	    graphEdges.add(
	            new EdgeSeed(
	                    "communication",
	                    "career-foundation"));

	    String previousKey = "career-foundation";

	    // =========================================================
	    // DOMAIN TOPICS
	    // =========================================================

	    for (String topic : safeList(domain)) {

	        if (isBlank(topic)) {
	            continue;
	        }

	        y += 180;

	        String key = createUniqueKey(
	                "domain-" + slug(topic),
	                usedKeys);

	        addDynamicNode(
	                graphNodes,
	                usedKeys,
	                key,
	                topic.trim(),
	                "Domain Learning",
	                0,
	                y);

	        graphEdges.add(
	                new EdgeSeed(
	                        previousKey,
	                        key));

	        previousKey = key;
	    }

	    // =========================================================
	    // PRACTICE TOPICS
	    // =========================================================

	    for (String topic : safeList(practice)) {

	        if (isBlank(topic)) {
	            continue;
	        }

	        y += 180;

	        String key = createUniqueKey(
	                "practice-" + slug(topic),
	                usedKeys);

	        addDynamicNode(
	                graphNodes,
	                usedKeys,
	                key,
	                topic.trim(),
	                "Practice",
	                0,
	                y);

	        graphEdges.add(
	                new EdgeSeed(
	                        previousKey,
	                        key));

	        previousKey = key;
	    }

	    // =========================================================
	    // PROJECTS
	    // =========================================================

	    for (String topic : safeList(projects)) {

	        if (isBlank(topic)) {
	            continue;
	        }

	        y += 180;

	        String key = createUniqueKey(
	                "project-" + slug(topic),
	                usedKeys);

	        addDynamicNode(
	                graphNodes,
	                usedKeys,
	                key,
	                topic.trim(),
	                "Portfolio",
	                0,
	                y);

	        graphEdges.add(
	                new EdgeSeed(
	                        previousKey,
	                        key));

	        previousKey = key;
	    }

	    // =========================================================
	    // PLACEMENT READINESS
	    // =========================================================

	    y += 180;

	    addDynamicNode(
	            graphNodes,
	            usedKeys,
	            "resume-readiness",
	            "Resume and Profile Readiness",
	            "Placement",
	            -300,
	            y);

	    addDynamicNode(
	            graphNodes,
	            usedKeys,
	            "interview-readiness",
	            role + " Interview Readiness",
	            "Placement",
	            0,
	            y);

	    addDynamicNode(
	            graphNodes,
	            usedKeys,
	            "job-readiness",
	            "Job Search and Application Readiness",
	            "Placement",
	            300,
	            y);

	    graphEdges.add(
	            new EdgeSeed(
	                    previousKey,
	                    "resume-readiness"));

	    graphEdges.add(
	            new EdgeSeed(
	                    previousKey,
	                    "interview-readiness"));

	    graphEdges.add(
	            new EdgeSeed(
	                    previousKey,
	                    "job-readiness"));

	    return new GraphSeed(
	            graphNodes,
	            graphEdges);
	}
}
