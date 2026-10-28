package com.launch.interviewservice.exception;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String m) {
		super(m);
	}
}