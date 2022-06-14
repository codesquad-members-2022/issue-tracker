package com.ron2ader.issuetracker.domain.issue;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    @Query("SELECT i FROM Issue i JOIN FETCH i.issuer m where i.issueNumber = :issueNumber")
    Optional<Issue> findByIssueNumber(Long issueNumber);

    @Query(value = "SELECT i FROM Issue i JOIN FETCH i.issuer m where i.openStatus = :openStatus",
            countQuery = "SELECT count(i.id) FROM Issue i")
    Page<Issue> findAllByOpenStatus(Pageable pageable, Boolean openStatus);
}
