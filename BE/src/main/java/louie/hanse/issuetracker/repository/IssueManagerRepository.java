package louie.hanse.issuetracker.repository;

import louie.hanse.issuetracker.domain.IssueManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueManagerRepository extends JpaRepository<IssueManager, Long> {
}
