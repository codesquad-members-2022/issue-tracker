package com.codesquad.issueTracker.milestone.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codesquad.issueTracker.issue.application.dto.FilterOutlineResponse;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

    @Query("SELECT NEW com.codesquad.issueTracker.issue.application.dto.FilterOutlineResponse(m.name) "
        + "FROM Milestone m")
    List<FilterOutlineResponse> findMilestonesOutlineInfo();

    Optional<Milestone> findByName(String name);
}
