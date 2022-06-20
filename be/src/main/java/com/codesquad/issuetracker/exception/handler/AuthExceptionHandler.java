package com.codesquad.issuetracker.exception.handler;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.codesquad.issuetracker.exception.domain.BusinessException;
import com.codesquad.issuetracker.exception.domain.type.AuthExceptionType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class AuthExceptionHandler {
//
//    @ExceptionHandler({IllegalArgumentException.class})
//    public ResponseEntity<ExceptionResponseDto> exceptionHandler(HttpServletRequest request, IllegalArgumentException ex) {
//        return ResponseEntity.ok().body(new ExceptionResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
//    }
//
//    @ExceptionHandler({SignatureVerificationException.class})
//    public ResponseEntity<ExceptionResponseDto> exceptionHandler(HttpServletRequest request, SignatureVerificationException ex) {
//        return ResponseEntity.ok().body(new ExceptionResponseDto(HttpStatus.UNAUTHORIZED.value(), ex.getMessage()));
//    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<String> exceptionHandler(HttpServletRequest request, TokenExpiredException exception) {
        AuthExceptionType exceptionType = AuthExceptionType.TOKEN_NOT_FOUND;
        return ResponseEntity.status(exceptionType.getStatusCode()).body(exceptionType.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusinessException(HttpServletRequest request, BusinessException exception) {
        return ResponseEntity.status(exception.getExceptionType().getStatusCode()).body(exception.getMessage());
    }
}
