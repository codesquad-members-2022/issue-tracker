package com.ron2ader.issuetracker.domain.issue;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IssueAssigneeRepository extends JpaRepository<IssueAssignee, Long> {

    @Query("select ia from IssueAssignee ia " +
            "join fetch ia.assignee " +
            "where ia.issue = :issue")
    List<IssueAssignee> findByIssue(@Param("issue") Issue issue);

}
