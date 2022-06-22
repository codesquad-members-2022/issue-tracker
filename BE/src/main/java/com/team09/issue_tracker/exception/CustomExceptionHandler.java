package com.team09.issue_tracker.exception;

import com.team09.issue_tracker.common.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse<String>> handleBusinessException(
		BusinessException exception) {
		return ResponseEntity.status(exception.getHttpStatus())
			.body(new ErrorResponse<>(exception.getBodyMessage()));
	}
}
