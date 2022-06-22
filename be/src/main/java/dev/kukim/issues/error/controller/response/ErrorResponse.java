package dev.kukim.issues.error.controller.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {

	private final int statusCode;
	private final String message;

	public ErrorResponse(HttpStatus httpStatus, String message) {
		this.statusCode = httpStatus.value();
		this.message = message;
	}
}
