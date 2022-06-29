package com.team09.issue_tracker.issue;

import com.team09.issue_tracker.issue.domain.IssueAssignee;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueAssigneeRepository extends JpaRepository<IssueAssignee, Long> {

	Set<IssueAssignee> findByIssueId(Long issueId);

}
