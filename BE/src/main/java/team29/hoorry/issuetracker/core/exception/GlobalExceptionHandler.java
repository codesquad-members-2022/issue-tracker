package team29.hoorry.issuetracker.core.exception;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team29.hoorry.issuetracker.core.exception.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AuthException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(AuthException error) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(error.getMessage()));
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException error) {
		return ResponseEntity.badRequest().body(new ErrorResponse(error.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException error) {
		BindingResult bindingResult = error.getBindingResult();

		StringBuilder message = new StringBuilder();
		if (bindingResult.hasErrors()) {
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				message.append(fieldError.getDefaultMessage());
			}
		}

		return ResponseEntity.badRequest().body(new ErrorResponse(message.toString()));
	}
}
