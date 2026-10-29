package com.launch.authservice.config; // Change to your actual package

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable())

				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

						.requestMatchers("/api/auth/**", "/actuator/health", "/error").permitAll()

						.anyRequest().authenticated())

				// Keep your existing JWT filter here if you have one:
				// .addFilterBefore(jwtAuthenticationFilter,
				// UsernamePasswordAuthenticationFilter.class)

				.build();
	}

	@Bean
	public UrlBasedCorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();

		config.setAllowedOrigins(List.of("http://localhost:5173", "http://localhost:3000",
				"https://launch-2csikrxc5-adigithub1.vercel.app"));

		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));

		config.setAllowedHeaders(
				Arrays.asList("Authorization", "Content-Type", "Accept", "Origin", "X-Requested-With"));

		config.setExposedHeaders(List.of("Authorization"));

		config.setAllowCredentials(true);
		config.setMaxAge(3600L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		source.registerCorsConfiguration("/**", config);

		return source;
	}
}