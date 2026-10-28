package com.launch.authservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "email_otps", indexes = @Index(name = "idx_otp_email", columnList = "email"))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailOtp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 320)
	private String email;
	@Column(name = "otp_hash", nullable = false, length = 100)
	private String otpHash;
	@Column(name = "verification_token_hash", length = 100)
	private String verificationTokenHash;
	@Column(name = "expires_at", nullable = false)
	private Instant expiresAt;
	@Column(nullable = false)
	private int attempts;
	@Column(nullable = false)
	private boolean verified;
	@Column(name = "created_at", nullable = false)
	private Instant createdAt;
}
