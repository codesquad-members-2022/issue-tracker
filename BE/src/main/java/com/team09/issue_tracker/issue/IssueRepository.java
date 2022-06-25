package com.team09.issue_tracker.issue;

import com.team09.issue_tracker.issue.domain.Issue;
import com.team09.issue_tracker.milestone.Milestone;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {

	@EntityGraph(attributePaths = {"milestone", "issueLabels", "issueLabels.label"})
	List<Issue> findByMemberIdAndIsOpened(Long memberId, boolean isOpened);

	Stream<Issue> findByMilestone(Milestone milestone);
}
