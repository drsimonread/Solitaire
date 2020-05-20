package edu.smcm.util;

@SuppressWarnings("serial")
public class ImplementationErrorException extends RuntimeException {
	// TODO This should be a hierarchy
	// TODO This should be called ImplementationError as it's a RuntimeException or should it be an Error?
	
	private Exception exception;
	private String message;
	
	public ImplementationErrorException() {
		this.exception = null;
		this.message = null;
	}
	
	public ImplementationErrorException(Exception exception) {
		this.exception = exception;
		this.message = null;
	}
	
	public ImplementationErrorException(String message) {
		this.exception = null;
		this.message = message;
	}
	
	public Exception exception() {
		return exception;
	}
	
	public String message() {
		return message;
	}
	
	public String toString() {
		String result;
		
		if (null != exception) { 
			result = "Implementation Error: " + exception;
		} else if (null != message) {
			result = "Implementation Error: " + message;
		} else {
			result = "Unknown implementation error.";
		}
		
		return result;
	}
}
