package com.ron2ader.issuetracker.domain.milestone;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

    @EntityGraph(attributePaths = "issues")
    @Override
    List<Milestone> findAll();
}
