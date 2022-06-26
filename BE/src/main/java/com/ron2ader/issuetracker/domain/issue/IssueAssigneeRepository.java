package com.ron2ader.issuetracker.domain.issue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueAssigneeRepository extends JpaRepository<IssueAssignee, Long> {

    @Query("select ia from IssueAssignee ia " +
            "join fetch ia.assignee " +
            "where ia.issue = :issue")
    List<IssueAssignee> findByIssue(@Param("issue") Issue issue);

}
