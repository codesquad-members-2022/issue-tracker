package kr.codesquad.issuetracker.dto;

import kr.codesquad.issuetracker.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponse {

    private Long id;
    private String name;
    private String email;
    private String githubId;
    private String imageUrl;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.githubId = member.getGithubId();
        this.imageUrl = member.getImageUrl();
    }
}
