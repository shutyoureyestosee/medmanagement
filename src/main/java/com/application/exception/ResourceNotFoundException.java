package com.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message, String string, Long id) {
        super(message);
    }

	public ResourceNotFoundException(String string) {
		// TODO Auto-generated constructor stub
	}

	
}
