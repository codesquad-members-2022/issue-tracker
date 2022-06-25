package com.team09.issue_tracker.exception;

import org.springframework.http.HttpStatus;

public abstract class BusinessException extends RuntimeException {

	protected abstract HttpStatus getHttpStatus();

	protected abstract String getBodyMessage();
}
