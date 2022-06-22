package com.team09.issue_tracker.issue;

import com.team09.issue_tracker.issue.domain.IssueAssignee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueAssigneeRepository extends JpaRepository<IssueAssignee, Long> {

	List<IssueAssignee> findByIssueId(Long issueId);

}
