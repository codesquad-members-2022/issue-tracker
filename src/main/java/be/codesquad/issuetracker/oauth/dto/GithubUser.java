package be.codesquad.issuetracker.oauth.dto;

import lombok.Getter;

@Getter
public class GithubUser {

    private String id;
    private String username;
    private String imageUrl;
}
