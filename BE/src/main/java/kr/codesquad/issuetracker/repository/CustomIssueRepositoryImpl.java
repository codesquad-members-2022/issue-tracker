package kr.codesquad.issuetracker.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.codesquad.issuetracker.domain.Issue;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static kr.codesquad.issuetracker.domain.QIssue.issue;
import static kr.codesquad.issuetracker.domain.QIssueLabels.issueLabels;

@RequiredArgsConstructor
public class CustomIssueRepositoryImpl implements CustomIssueRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Issue> findOpenedIssues() {
        JPAQuery<Issue> query = jpaQueryFactory
                .select(issue)
                .from(issue)
                .join(issue.member).fetchJoin()
                .leftJoin(issue.issueLabelsList, issueLabels).fetchJoin()
                .leftJoin(issueLabels.label).fetchJoin()
                .where(issue.status.isTrue())
                .distinct();
        List<Issue> result = query.fetch();
        return result;
    }
}
