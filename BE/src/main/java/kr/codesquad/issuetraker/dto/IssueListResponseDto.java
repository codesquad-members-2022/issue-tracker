package kr.codesquad.issuetraker.dto;

import kr.codesquad.issuetraker.domain.issue.Issue;
import kr.codesquad.issuetraker.domain.label.Label;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IssueListResponseDto {
    private Long issueId;
    private String title;
    private String description;
    private String milestoneTitle;
    private Label label;

    public static IssueListResponseDto of(Issue issue) {
        return IssueListResponseDto.builder()
                .issueId(issue.getId())
                .title(issue.getTitle())
                .description(issue.getDescription())
                .milestoneTitle(issue.getMilestone().getTitle())
                .label(issue.getLabel())
                .build();
    }

}
