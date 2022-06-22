package dev.kukim.issues.error.controller;

import dev.kukim.issues.error.controller.response.ErrorResponse;
import dev.kukim.issues.user.auth.exception.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class exceptionControllerAdvice {

	@ExceptionHandler(AuthorizationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErrorResponse authorizationExceptionHandler(
		AuthorizationException authorizationException) {
		return new ErrorResponse(HttpStatus.UNAUTHORIZED,
			authorizationException.getMessage());
	}

}
