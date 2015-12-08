package com.bt.jpa.common.exception;

public class PmsServiceException extends Exception {
	private static final long serialVersionUID = -2476914400673872934L;

	public PmsServiceException() {
		super();
	}

	public PmsServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PmsServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public PmsServiceException(String message) {
		super(message);
	}

	public PmsServiceException(Throwable cause) {
		super(cause);
	}

}
