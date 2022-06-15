package com.ron2ader.issuetracker.domain.issue;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    @Query("SELECT i FROM Issue i JOIN FETCH i.issuer m WHERE i.id = :id")
    Optional<Issue> findById(@Param("id") Long id);

    @Query(value = "SELECT i FROM Issue i JOIN FETCH i.issuer m WHERE i.openStatus = :openStatus",
            countQuery = "SELECT count(i.id) FROM Issue i WHERE i.openStatus = :openStatus")
    Page<Issue> findAllByOpenStatus(Pageable pageable, @Param("openStatus") Boolean openStatus);
}
