package kr.codesquad.issuetracker.repository;

import kr.codesquad.issuetracker.domain.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long>, CustomIssueRepository {

}
