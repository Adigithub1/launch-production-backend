package com.launch.studentservice.dto.response;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfileResponse {

    private Long id;

    private Long userId;

    private String name;

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

    private Integer profileCompletion;

    private Instant createdAt;

    private Instant updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Integer getProfileCompletion() {
		return profileCompletion;
	}

	public void setProfileCompletion(Integer profileCompletion) {
		this.profileCompletion = profileCompletion;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "StudentProfileResponse [id=" + id + ", userId=" + userId + ", name=" + name + ", email=" + email
				+ ", phone=" + phone + ", stream=" + stream + ", branch=" + branch + ", specialization="
				+ specialization + ", targetRole=" + targetRole + ", designation=" + designation + ", college="
				+ college + ", graduationYear=" + graduationYear + ", skills=" + skills + ", interests=" + interests
				+ ", resumeKey=" + resumeKey + ", profileCompletion=" + profileCompletion + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
    
}