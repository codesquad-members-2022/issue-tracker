package kr.codesquad.issuetracker.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GitHubUserInfo {

    @JsonProperty("login")
    private String userId;
    private String email;
    private String name;

}
