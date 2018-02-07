package com.pointwest.java.casestudy.exception;

public class DatabaseException extends Exception {
	
	private String errorMessage;

	public DatabaseException(String ErrorMessage) {
		this.errorMessage = ErrorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
