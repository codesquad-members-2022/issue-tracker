package com.team33.backend.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IssueTrackerRuntimeException.class)
    public ResponseEntity<ErrorResponse> businessException(IssueTrackerRuntimeException exception) {
        return ResponseEntity.status(exception.getStatus())
                .body(ErrorResponse.create(exception));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> unresolvedException() {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(internalServerError)
                .body(new ErrorResponse("0000", "처리되지 않은 예외가 발생하였습니다.", internalServerError));
    }
}
