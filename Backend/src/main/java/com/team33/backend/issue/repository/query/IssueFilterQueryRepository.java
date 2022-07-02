package com.team33.backend.issue.repository.query;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team33.backend.issue.domain.Issue;
import com.team33.backend.issue.domain.IssueStatus;
import com.team33.backend.issue.domain.QIssue;
import com.team33.backend.issue.domain.filter.IssueFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.team33.backend.comment.domain.QComment.comment;
import static com.team33.backend.issue.domain.QAssignee.assignee;

@Repository
public class IssueFilterQueryRepository extends QuerydslRepositorySupport {

    private static QIssue issue = QIssue.issue;

    private final JPAQueryFactory queryFactory;

    public IssueFilterQueryRepository(JPAQueryFactory queryFactory) {
        super(Issue.class);
        this.queryFactory = queryFactory;
    }

    public List<Issue> findAllFilteredIssues(List<IssueFilter> issueFilters, long issueGroupId, long memberId, Pageable pageable) {
        JPAQuery<Issue> filteredIssuesQuery = queryFactory.selectFrom(issue)
                .where(issue.issueGroup.id.eq(issueGroupId));

        for (IssueFilter issueFilter : issueFilters) {
            switch (issueFilter) {
                case OPEN: filteredIssuesQuery = filteredIssuesQuery.where(issue.issueStatus.eq(IssueStatus.OPEN)); break;
                case CLOSED: filteredIssuesQuery = filteredIssuesQuery.where(issue.issueStatus.eq(IssueStatus.CLOSED)); break;
                case AUTHOR_ME: filteredIssuesQuery = filteredIssuesQuery.where(issue.author.id.eq(memberId)); break;
                case COMMENTED_ME: filteredIssuesQuery = filteredIssuesQuery
                        .innerJoin(comment)
                        .on(comment.issue.id.eq(issue.id))
                        .on(comment.member.id.eq(memberId)); break;
                case ASSIGNED_ME: filteredIssuesQuery = filteredIssuesQuery
                        .innerJoin(assignee)
                        .on(assignee.issue.id.eq(issue.id))
                        .on(assignee.member.id.eq(memberId)); break;
                default: break;
            }
        }

        Querydsl querydsl = getQuerydsl();

        return querydsl.applyPagination(pageable, filteredIssuesQuery)
                .fetch();
    }
}
