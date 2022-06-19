package be.codesquad.issuetracker.oauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GithubUser {

    private String githubId;
    private String username;
    private String imageUrl;
}
