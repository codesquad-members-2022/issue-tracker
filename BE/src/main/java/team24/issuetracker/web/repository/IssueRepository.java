package team24.issuetracker.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team24.issuetracker.domain.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
}
