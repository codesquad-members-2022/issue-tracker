package louie.hanse.issuetracker.repository;

import louie.hanse.issuetracker.domain.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
}
