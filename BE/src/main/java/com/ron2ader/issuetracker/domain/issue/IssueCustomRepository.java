package com.ron2ader.issuetracker.domain.issue;

import com.ron2ader.issuetracker.controller.issuedto.IssueFilter;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.query.Param;

public interface IssueCustomRepository {

    Optional<Issue> findIssueById(@Param("id") Long id);

    List<Issue> findByIssueFilter(IssueFilter issueFilter);

}
