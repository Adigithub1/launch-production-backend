package com.launch.studentservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


public class StudentProfileRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    private String name;

    @Email(message = "Invalid email")
    private String email;

    private String phone;

    private String stream;

    private String branch;

    private String specialization;

    private String targetRole;

    private String designation;

    private String college;

    private String graduationYear;

    private String skills;

    private String interests;

    private String resumeKey;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getTargetRole() {
		return targetRole;
	}

	public void setTargetRole(String targetRole) {
		this.targetRole = targetRole;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public String getResumeKey() {
		return resumeKey;
	}

	public void setResumeKey(String resumeKey) {
		this.resumeKey = resumeKey;
	}

	@Override
	public String toString() {
		return "StudentProfileRequest [userId=" + userId + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", stream=" + stream + ", branch=" + branch + ", specialization=" + specialization + ", targetRole="
				+ targetRole + ", designation=" + designation + ", college=" + college + ", graduationYear="
				+ graduationYear + ", skills=" + skills + ", interests=" + interests + ", resumeKey=" + resumeKey + "]";
	}
    
}