package kr.codesquad.issuetracker.auth.jwt;

import lombok.Getter;

@Getter
public class JWT {

    private final String jwt;

    public JWT(String jwt) {
        this.jwt = jwt;
    }
}
