package com.team33.backend.common.exception.member;

import com.team33.backend.common.exception.IssueTrackerRuntimeException;

public abstract class MemberRuntimeException extends IssueTrackerRuntimeException {

    public MemberRuntimeException(String message) {
        super(message);
    }

    public MemberRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
