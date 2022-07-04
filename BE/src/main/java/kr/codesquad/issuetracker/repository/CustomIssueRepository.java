package kr.codesquad.issuetracker.repository;

import kr.codesquad.issuetracker.domain.Issue;

import java.util.List;

public interface CustomIssueRepository {

    List<Issue> findOpenedIssues();
}
