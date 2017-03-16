package com.ihbaby.sys.spring;

public class SignRuntimeException extends RuntimeException {
	
	
	private static final long serialVersionUID = 4219294359987392934L;
	
	public SignRuntimeException() {
	}
	
	public SignRuntimeException(String message) {
		super(message);
	}
	
	public SignRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SignRuntimeException(Throwable cause) {
		super(cause);
	}
	
	public SignRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
