package codesquad.issuetracker.dto.issue;

import codesquad.issuetracker.domain.IssueStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueSearchCondition {

    private IssueStatus status = IssueStatus.OPEN;
    private String writer;
    private String assignee;
    private String replier;
    private String label;
    private String milestone;
    private String exclusions;
}
