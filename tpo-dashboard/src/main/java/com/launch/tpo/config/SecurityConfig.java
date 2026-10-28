package com.launch.tpo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
	@Bean
	SecurityFilterChain chain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable()).cors(Customizer.withDefaults())
				.authorizeHttpRequests(a -> a.requestMatchers("/actuator/health").permitAll()
						.requestMatchers("/api/tpo/**").permitAll().anyRequest().authenticated())
				.build();
	}

	@Bean
	CorsConfigurationSource cors(@Value("${launch.frontend-origin}") String origin) {
		var c = new CorsConfiguration();
		c.setAllowedOrigins(List.of(origin));
		c.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		c.setAllowedHeaders(List.of("*"));
		c.setAllowCredentials(true);
		var s = new UrlBasedCorsConfigurationSource();
		s.registerCorsConfiguration("/**", c);
		return s;
	}
}
