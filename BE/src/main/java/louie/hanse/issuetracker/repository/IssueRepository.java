package louie.hanse.issuetracker.repository;

import louie.hanse.issuetracker.domain.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long>, CustomIssueRepository {
}
