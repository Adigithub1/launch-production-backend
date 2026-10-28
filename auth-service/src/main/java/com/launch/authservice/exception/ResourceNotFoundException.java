package com.launch.authservice.exception;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String m) {
		super(m);
	}
}