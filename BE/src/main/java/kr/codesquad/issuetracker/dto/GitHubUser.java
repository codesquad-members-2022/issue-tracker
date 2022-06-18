package kr.codesquad.issuetracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import kr.codesquad.issuetracker.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubUser {

    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("login")
    private String githubId;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    public Member createMember() {
        return Member.builder()
                .githubId(githubId)
                .email(email)
                .name(name)
                .imageUrl(avatarUrl)
                .build();
    }
}
