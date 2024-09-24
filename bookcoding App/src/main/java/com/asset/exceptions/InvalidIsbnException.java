package com.asset.exceptions;

public class InvalidIsbnException extends Exception {
	

	private static final long serialVersionUID = 1L;
	private String message;

	public String getMessage() {
		return message;
	}
	
	public InvalidIsbnException(String message) {
		super();
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
