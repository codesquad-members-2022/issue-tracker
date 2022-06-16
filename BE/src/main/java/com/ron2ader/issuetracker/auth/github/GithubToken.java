package com.ron2ader.issuetracker.auth.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GithubToken {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;

    public String toHeader() {
        return this.tokenType + " " + this.accessToken;
    }
}
