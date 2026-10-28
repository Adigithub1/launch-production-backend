package com.launch.roadmapservice.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	ResponseEntity<Map<String, Object>> notFound(ResourceNotFoundException ex) {
		return ResponseEntity.status(404).body(Map.of("message", ex.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	ResponseEntity<Map<String, Object>> general(Exception ex) {
		return ResponseEntity.status(500).body(Map.of("message", ex.getMessage()));
	}
}
