package kr.codesquad.issuetracker.domain.milestone.repository;

import kr.codesquad.issuetracker.domain.milestone.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

}
