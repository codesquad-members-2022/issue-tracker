package com.ron2ader.issuetracker.domain.issue;

import com.ron2ader.issuetracker.controller.issuedto.IssueFilter;
import java.util.List;

public interface IssueCustomRepository {

    List<Issue> findByIssueFilter(IssueFilter issueFilter);

}
