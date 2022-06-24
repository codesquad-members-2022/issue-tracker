package kr.codesquad.issuetracker.domain.milestone.repository;

import java.util.List;
import kr.codesquad.issuetracker.domain.Status;
import kr.codesquad.issuetracker.domain.milestone.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

	long countByStatus(Status status);

	List<Milestone> findAllByStatus(Status status);
}
