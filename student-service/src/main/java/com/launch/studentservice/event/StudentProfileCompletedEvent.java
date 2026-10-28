package com.launch.studentservice.event;

import java.util.ArrayList;
import java.util.List;

public class StudentProfileCompletedEvent {
	private Long studentId;
	private Long userId;
	private Long collegeId;
	private String stream;
	private String degree;
	private String branch;
	private String specialization;
	private String targetRole;
	private String graduationYear;
	private List<String> currentSkills = new ArrayList<>();
	private List<String> interests = new ArrayList<>();

	public StudentProfileCompletedEvent() {
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long v) {
		studentId = v;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long v) {
		userId = v;
	}

	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long v) {
		collegeId = v;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String v) {
		stream = v;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String v) {
		degree = v;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String v) {
		branch = v;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String v) {
		specialization = v;
	}

	public String getTargetRole() {
		return targetRole;
	}

	public void setTargetRole(String v) {
		targetRole = v;
	}

	public String getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(String v) {
		graduationYear = v;
	}

	public List<String> getCurrentSkills() {
		return currentSkills;
	}

	public void setCurrentSkills(List<String> v) {
		currentSkills = v == null ? new ArrayList<>() : v;
	}

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> v) {
		interests = v == null ? new ArrayList<>() : v;
	}
}
