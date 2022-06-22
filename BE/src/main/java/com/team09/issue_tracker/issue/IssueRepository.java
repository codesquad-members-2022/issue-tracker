package com.team09.issue_tracker.issue;

import com.team09.issue_tracker.issue.domain.Issue;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {

	@EntityGraph(attributePaths = {"milestone", "issueLabels", "issueLabels.label"})
	List<Issue> findByMemberIdAndIsOpened(Long memberId, boolean isOpened);

}
