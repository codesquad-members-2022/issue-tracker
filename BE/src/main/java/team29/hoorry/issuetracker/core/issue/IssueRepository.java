package team29.hoorry.issuetracker.core.issue;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import team29.hoorry.issuetracker.core.issue.domain.Issue;
import team29.hoorry.issuetracker.core.milestone.domain.Milestone;

public interface IssueRepository extends JpaRepository<Issue, Long>, IssueCustomRepository {

	List<Issue> findByMilestone(Milestone milestone);
}
