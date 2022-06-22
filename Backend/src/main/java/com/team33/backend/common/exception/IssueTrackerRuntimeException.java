package com.team33.backend.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class IssueTrackerRuntimeException extends RuntimeException {

    public IssueTrackerRuntimeException(String message) {
        super(message);
    }

    public IssueTrackerRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    protected abstract String getErrorCode();

    protected abstract HttpStatus getStatus();
}
