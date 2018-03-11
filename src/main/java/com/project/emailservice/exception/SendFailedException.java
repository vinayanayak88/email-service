package com.project.emailservice.exception;

public class SendFailedException extends Exception {
	
	private String errorMessage;

	public SendFailedException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
