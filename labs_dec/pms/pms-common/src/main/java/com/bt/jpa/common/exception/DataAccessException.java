package com.bt.jpa.common.exception;

public class DataAccessException extends Exception {
	private static final long serialVersionUID = -2476914400673872934L;

	public DataAccessException() {
		super();
	}

	public DataAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataAccessException(String message) {
		super(message);
	}

	public DataAccessException(Throwable cause) {
		super(cause);
	}

}
