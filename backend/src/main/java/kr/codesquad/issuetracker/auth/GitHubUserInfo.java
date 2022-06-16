package kr.codesquad.issuetracker.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.codesquad.issuetracker.user.User;
import lombok.Getter;

@Getter
public class GitHubUserInfo {

    @JsonProperty("login")
    private String userId;
    private String email;
    private String name;

    public User createUser() {
        return new User(userId, email, name);
    }
}
