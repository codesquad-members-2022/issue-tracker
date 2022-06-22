package kr.codesquad.issuetracker.exception.handler;

import kr.codesquad.issuetracker.exception.CustomException;
import kr.codesquad.issuetracker.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {CustomException.class})
	protected ResponseEntity<ErrorResponse> customException(CustomException e) {
		log.error("CustomException.ErrorMessage = {}", e.getErrorMessage());
		return ErrorResponse.toResponseEntity(e.getErrorMessage());
	}
}
