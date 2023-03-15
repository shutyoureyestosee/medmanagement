package com.application.exception;

public class AppointmentAlreadyBookedException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppointmentAlreadyBookedException(String message) {
        super(message);
    }
}

