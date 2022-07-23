package louie.hanse.issuetracker.repository;

import louie.hanse.issuetracker.domain.Issue;
import louie.hanse.issuetracker.web.dto.issue.IssueSearchRequest;

import java.util.List;

public interface CustomIssueRepository {
    List<Issue> search(IssueSearchRequest issueSearchRequest, Long userId);
    long searchReverseStatusCount(IssueSearchRequest issueSearchRequest, Long userId);
}
