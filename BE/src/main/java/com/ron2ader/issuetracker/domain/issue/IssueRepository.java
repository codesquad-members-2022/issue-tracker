package com.ron2ader.issuetracker.domain.issue;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    @Query("SELECT i FROM Issue i JOIN FETCH i.issuer m")
    Optional<Issue> findByIssueNumber(Long issueNumber);
}
