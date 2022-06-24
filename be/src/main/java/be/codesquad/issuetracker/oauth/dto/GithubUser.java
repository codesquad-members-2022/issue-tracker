package be.codesquad.issuetracker.oauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GithubUser {

    @JsonProperty("node_id")
    private String authId;

    @JsonProperty("login")
    private String username;

    @JsonProperty("avatar_url")
    private String imageUrl;
}
