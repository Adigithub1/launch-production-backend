package com.launch.roadmapservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roadmaps", uniqueConstraints = @UniqueConstraint(name = "uk_roadmap_student", columnNames = "student_id"))

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Roadmap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "student_id", nullable = false)
	private Long studentId;
	@Column(nullable = false)
	private Long version;
	@Column(name = "target_role", nullable = false)
	private String targetRole;
	@Column(nullable = false)
	private String label;
	@Column(nullable = false)
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getTargetRole() {
		return targetRole;
	}

	public void setTargetRole(String targetRole) {
		this.targetRole = targetRole;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Roadmap [id=" + id + ", studentId=" + studentId + ", version=" + version + ", targetRole=" + targetRole
				+ ", label=" + label + ", status=" + status + "]";
	}

}
