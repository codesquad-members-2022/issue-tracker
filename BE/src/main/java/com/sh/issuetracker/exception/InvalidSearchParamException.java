package com.sh.issuetracker.exception;

public class InvalidSearchParamException extends RuntimeException {
	public InvalidSearchParamException(String message) {
		super(message);
	}
}
