package louie.hanse.issuetracker.repository;

import louie.hanse.issuetracker.domain.IssueLabel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueLabelRepository extends JpaRepository<IssueLabel, Long> {
}
