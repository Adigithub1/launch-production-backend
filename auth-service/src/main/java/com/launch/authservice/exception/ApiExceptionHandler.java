package com.launch.authservice.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.*;

@RestControllerAdvice
public class ApiExceptionHandler {
	@ExceptionHandler({ IllegalArgumentException.class, IllegalStateException.class })
	ResponseEntity<Map<String, Object>> bad(RuntimeException e) {
		return ResponseEntity.badRequest()
				.body(Map.of("timestamp", Instant.now().toString(), "message", e.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<Map<String, Object>> validation(MethodArgumentNotValidException e) {
		String m = e.getBindingResult().getFieldErrors().stream().findFirst().map(x -> x.getDefaultMessage())
				.orElse("Validation failed");
		return ResponseEntity.badRequest().body(Map.of("timestamp", Instant.now().toString(), "message", m));
	}
}
