package louie.hanse.issuetracker.web.dto;

import java.util.List;

public class IssueSearchResponse {
    private List<IssueResponse> issues;
    private int openedIssueCount;
    private int closedIssueCount;
}
