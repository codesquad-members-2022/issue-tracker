package codesquad.issuetracker.controller;

import codesquad.issuetracker.dto.ResponseMessage;
import codesquad.issuetracker.exception.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionControllerAdvice {

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ResponseMessage> handleJwtException(InvalidTokenException exception) {
        ResponseMessage message = new ResponseMessage(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }
}
