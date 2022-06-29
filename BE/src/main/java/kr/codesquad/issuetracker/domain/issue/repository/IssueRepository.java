package kr.codesquad.issuetracker.domain.issue.repository;

import java.util.List;
import kr.codesquad.issuetracker.domain.Status;
import kr.codesquad.issuetracker.domain.issue.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long>{

	long countByStatus(Status status);

	List<Issue> findAllByStatus(Status status);
}
