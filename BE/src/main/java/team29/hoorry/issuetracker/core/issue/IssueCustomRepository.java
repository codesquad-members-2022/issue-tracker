package team29.hoorry.issuetracker.core.issue;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team29.hoorry.issuetracker.core.issue.domain.Issue;
import team29.hoorry.issuetracker.core.issue.domain.Status;
import team29.hoorry.issuetracker.core.issue.dto.IssueFilter;

public interface IssueCustomRepository {

	Page<Issue> findAllByIssueFilter(IssueFilter issueFilter, Pageable pageable);

	Long countFilteredIssues(IssueFilter issueFilter, Status status);

	Optional<Issue> findByIdUsingFetchJoin(Long id);

	Long updateAllStatus(Status status, List<Long> issueIds);
}
