package com.team09.issue_tracker.milestone;

import com.team09.issue_tracker.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

	long countByIdAndWriter(Long id , Member writer);

}
