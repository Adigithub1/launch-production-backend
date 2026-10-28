package com.launch.placementservice.exception;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	ResponseEntity<?> notFound(Exception e) {
		return ResponseEntity.status(404).body(Map.of("timestamp", Instant.now(), "message", e.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<?> validation(MethodArgumentNotValidException e) {
		var m = new LinkedHashMap<String, String>();
		e.getBindingResult().getFieldErrors().forEach(x -> m.put(x.getField(), x.getDefaultMessage()));
		return ResponseEntity.badRequest().body(m);
	}

	@ExceptionHandler(Exception.class)
	ResponseEntity<?> other(Exception e) {
		return ResponseEntity.badRequest()
				.body(Map.of("timestamp", Instant.now(), "message", String.valueOf(e.getMessage())));
	}
}