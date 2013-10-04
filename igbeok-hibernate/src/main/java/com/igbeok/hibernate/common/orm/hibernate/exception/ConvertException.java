package com.igbeok.hibernate.common.orm.hibernate.exception;

public class ConvertException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConvertException() {
	}

	public ConvertException(String message) {
		super(message);
	}

	public ConvertException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConvertException(Throwable cause) {
		super(cause);
	}

}
