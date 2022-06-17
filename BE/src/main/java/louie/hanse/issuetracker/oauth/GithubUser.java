package louie.hanse.issuetracker.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class GithubUser {
    @JsonProperty("login")
    private String userName;

    @JsonProperty("avatar_url")
    private String avatarImageURL;
}
