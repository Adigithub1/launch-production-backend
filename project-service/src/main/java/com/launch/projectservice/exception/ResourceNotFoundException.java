package com.launch.projectservice.exception;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String m) {
		super(m);
	}
}