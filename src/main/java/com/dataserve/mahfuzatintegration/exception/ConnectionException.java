package com.dataserve.mahfuzatintegration.exception;

public class ConnectionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5892154978860173772L;
	
	public ConnectionException(String message) {
		super(message);
	}

	public ConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
}
