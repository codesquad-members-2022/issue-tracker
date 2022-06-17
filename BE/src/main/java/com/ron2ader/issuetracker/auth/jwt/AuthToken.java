package com.ron2ader.issuetracker.auth.jwt;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthToken {

    private static final String BEARER_TYPE = "Bearer";

    private String tokenType;
    private String token;

    public static AuthToken from(String tokenHeader) {
        String[] split = tokenHeader.split(" ");
        if (split.length != 2 || validateToken(tokenHeader)) {
            throw new IllegalArgumentException();
        }

        return new AuthToken(split[0], split[1]);
    }

    private static boolean validateToken(String tokenType) {
        if (tokenType.equalsIgnoreCase(BEARER_TYPE)) {
            return true;
        }
        return false;
    }

}
