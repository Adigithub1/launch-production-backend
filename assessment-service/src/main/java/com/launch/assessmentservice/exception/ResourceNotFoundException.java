package com.launch.assessmentservice.exception;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String m) {
		super(m);
	}
}