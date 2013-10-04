package com.igbeok.hibernate.common.util.properties.exception;

public class LoadResourceException extends RuntimeException {

	public LoadResourceException() {
	}

	public LoadResourceException(String message) {
		super(message);
	}

	public LoadResourceException(String message, Throwable e){
		super(message, e);
	}

	public LoadResourceException(Throwable e) {
		super(e);
	}

}
