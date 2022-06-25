package com.team09.issue_tracker.issue;

import com.team09.issue_tracker.issue.domain.IssueLabel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueLabelRepository extends JpaRepository<IssueLabel, Long> {

}
