package kr.codesquad.issuetracker.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponse {

    private final String jwt;
    private final String message;

    public LoginResponse(String jwt, String message) {
        this.jwt = jwt;
        this.message = message;
    }
}
