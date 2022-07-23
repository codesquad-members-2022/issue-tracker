package louie.hanse.issuetracker.web.dto.issue;

import lombok.Getter;
import louie.hanse.issuetracker.domain.Issue;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class IssueSearchResponse {
    private List<IssueResponse> issues;
    private long openedIssueCount;
    private long closedIssueCount;

    public IssueSearchResponse(List<Issue> issues, long openedIssueCount, long closedIssueCount) {
        this.issues = issues.stream()
                .map(IssueResponse::new)
                .collect(Collectors.toList());
        this.openedIssueCount = openedIssueCount;
        this.closedIssueCount = closedIssueCount;
    }
}
