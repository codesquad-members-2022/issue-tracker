package codesquad.issuetracker.dto.issue;

import lombok.Getter;

@Getter
public class IssueCountDto {

    private Long countOfOpenIssues;
    private Long countOfClosedIssues;

    public IssueCountDto(Long countOfOpenIssues, Long countOfClosedIssues) {
        this.countOfOpenIssues = countOfOpenIssues;
        this.countOfClosedIssues = countOfClosedIssues;
    }
}
