package com.pointwest.java.casestudy.exception;

public class LoginException extends Exception {
	private final String LOGIN_ERROR_MESSAGE = "Invalid username & password.";
	
	private String status;
	
	public String getErrorMessage() {
		return LOGIN_ERROR_MESSAGE;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
