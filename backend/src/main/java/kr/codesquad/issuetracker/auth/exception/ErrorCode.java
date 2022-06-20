package kr.codesquad.issuetracker.auth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "가입된 유저 정보가 없습니다."),
    TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST, "클라이언트로부터 토큰이 전달되지 않았습니다."),
    TOKEN_TYPE_INCORRECT(HttpStatus.BAD_REQUEST, "Bearer 타입의 토큰이 전달되지 않았습니다.");


    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
