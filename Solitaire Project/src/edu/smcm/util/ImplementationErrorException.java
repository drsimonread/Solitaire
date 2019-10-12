package edu.smcm.util;

@SuppressWarnings("serial")
public class ImplementationErrorException extends RuntimeException {
	
	private Exception exception;
	
	public ImplementationErrorException(Exception exception) {
		this.exception = exception;
	}
	
	public Exception exception() {
		return exception;
	}
	
	public String toString() {
		return "Implementation Error: " + exception;
	}
}
