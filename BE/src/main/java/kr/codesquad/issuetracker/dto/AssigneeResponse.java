package kr.codesquad.issuetracker.dto;

import kr.codesquad.issuetracker.domain.Member;
import lombok.Getter;

@Getter
public class AssigneeResponse {

    private Long id;
    private String githubId;
    private String imageUrl;

    public AssigneeResponse(Member member) {
        this.id = member.getId();
        this.githubId = member.getGithubId();
        this.imageUrl = member.getImageUrl();
    }
}
