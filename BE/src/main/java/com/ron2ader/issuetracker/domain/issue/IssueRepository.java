package com.ron2ader.issuetracker.domain.issue;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface IssueRepository extends JpaRepository<Issue, Long>, IssueCustomRepository {

    Long countByOpenStatus(Boolean openStatus);

    @EntityGraph(attributePaths = {"milestone", "milestone.issues"})
    @Override
    Optional<Issue> findById(@Param("id") Long id);

    Page<Issue> findAllByOpenStatus(Pageable pageable, @Param("openStatus") Boolean openStatus);
}
