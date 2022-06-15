package com.ron2ader.issuetracker.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GithubUserInfo {

    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("avatar_url")
    private String avatarUrl;
}
