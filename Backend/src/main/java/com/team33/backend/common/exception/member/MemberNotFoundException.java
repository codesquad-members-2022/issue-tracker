package com.team33.backend.common.exception.member;

import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends MemberRuntimeException {

    private static final String ERROR_CODE = "M001";
    private static final String ERROR_MSG = "해당 유저를 찾을 수 없습니다.";
    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    public MemberNotFoundException() {
        super(ERROR_MSG);
    }

    @Override
    protected String getErrorCode() {
        return ERROR_CODE;
    }

    @Override
    protected HttpStatus getStatus() {
        return HTTP_STATUS;
    }
}
