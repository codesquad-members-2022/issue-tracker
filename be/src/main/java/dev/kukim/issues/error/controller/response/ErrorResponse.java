package dev.kukim.issues.error.controller.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ErrorResponse {

	private int statusCode;
	private String message;

	public ErrorResponse(HttpStatus httpStatus, String message) {
		this.statusCode = httpStatus.value();
		this.message = message;
	}
}
