package com.team33.backend.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Issue, Label, MileStone, Member, Comment
 * */
@Getter
public class ErrorResponse {

    private final String errorCode;
    private final String message;
    private final HttpStatus httpStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime time;

    public ErrorResponse(String errorCode, String message, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
        this.time = LocalDateTime.now();
    }

    public static ErrorResponse create(IssueTrackerRuntimeException exception) {
        return new ErrorResponse(exception.getErrorCode(), exception.getMessage(), exception.getStatus());
    }
}
