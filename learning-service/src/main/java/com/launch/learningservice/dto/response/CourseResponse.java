package com.launch.learningservice.dto.response;

public class CourseResponse {

    private Long id;
    private String title;
    private String category;
    private String stream;
    private String description;
    private String difficulty;
    private String status;
    private String roadmapKey;
    private String nodeKey;
    private Long studentRoadmapId;
    private String sourceType;
    private Integer durationMinutes;
    private Integer sequenceNumber;

    public CourseResponse() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getStream() { return stream; }
    public void setStream(String stream) { this.stream = stream; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getRoadmapKey() { return roadmapKey; }
    public void setRoadmapKey(String roadmapKey) { this.roadmapKey = roadmapKey; }
    public String getNodeKey() { return nodeKey; }
    public void setNodeKey(String nodeKey) { this.nodeKey = nodeKey; }
    public Long getStudentRoadmapId() { return studentRoadmapId; }
    public void setStudentRoadmapId(Long studentRoadmapId) { this.studentRoadmapId = studentRoadmapId; }
    public String getSourceType() { return sourceType; }
    public void setSourceType(String sourceType) { this.sourceType = sourceType; }
    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
    public Integer getSequenceNumber() { return sequenceNumber; }
    public void setSequenceNumber(Integer sequenceNumber) { this.sequenceNumber = sequenceNumber; }
}
