package com.ron2ader.issuetracker.domain.issue;

import static com.ron2ader.issuetracker.domain.issue.QIssue.issue;
import static com.ron2ader.issuetracker.domain.issue.QIssueAssignee.issueAssignee;
import static com.ron2ader.issuetracker.domain.issue.QIssueLabel.issueLabel;
import static com.ron2ader.issuetracker.domain.label.QLabel.label;
import static com.ron2ader.issuetracker.domain.member.QMember.member;
import static com.ron2ader.issuetracker.domain.milestone.QMilestone.milestone;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ron2ader.issuetracker.controller.issuedto.IssueFilter;
import com.ron2ader.issuetracker.domain.label.Label;
import com.ron2ader.issuetracker.domain.member.Member;
import com.ron2ader.issuetracker.domain.milestone.Milestone;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IssueRepositoryImpl implements IssueCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Issue> findIssueById(Long id) {
        return Optional.ofNullable(jpaQueryFactory
            .selectFrom(issue)
            .leftJoin(issue.issuer, member)
            .where(
                issuerIdEq(id)
            )
            .fetchOne());
    }

    @Override
    public List<Issue> findByIssueFilter(IssueFilter issueFilter) {
        return jpaQueryFactory
            .select(issue)
            .from(issue)
            .leftJoin(issue.assignees, issueAssignee)
            .leftJoin(issueAssignee.assignee, member)
            .leftJoin(issue.labels, issueLabel)
            .leftJoin(issueLabel.label, label)
            .leftJoin(issue.milestone, milestone)
            .where(
                issuerEq(issueFilter.getIssuer()),
                issueAssigneeEq(issueFilter.getAssignee()),
                issueLabelEq(issueFilter.getLabel()),
                milestoneEq(issueFilter.getMilestone())
            )
            .fetch();
    }

    private BooleanExpression milestoneEq(Milestone milestone) {
        if (milestone == null) {
            return null;
        }

        return issue.milestone.eq(milestone);
    }

    private BooleanExpression issueLabelEq(Label label) {
        if (label == null) {
            return null;
        }

        return issueLabel.label.eq(label);
    }

    private BooleanExpression issuerIdEq(Long id) {
        return issue.issuer.id.eq(id);
    }

    private BooleanExpression issuerEq(Member issuer) {
        if (issuer == null) {
            return null;
        }

        return issue.issuer.eq(issuer);
    }

    private BooleanExpression issueAssigneeEq(Member assignee) {
        if (assignee == null) {
            return null;
        }

        return issueAssignee.assignee.eq(assignee);
    }
}
