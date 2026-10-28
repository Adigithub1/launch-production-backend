package com.launch.authservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.*;

@Service
public class JwtService {
	private final SecretKey key;
	private final long expiration;

	public JwtService(@Value("${launch.jwt.secret}") String secret,
			@Value("${launch.jwt.expiration-seconds}") long expiration) {
		this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
		this.expiration = expiration;
	}

	public String create(Long id, String email, String role) {
		Instant now = Instant.now();
		return Jwts.builder().subject(email).claim("uid", id).claim("role", role).issuedAt(Date.from(now))
				.expiration(Date.from(now.plusSeconds(expiration))).signWith(key).compact();
	}
}
