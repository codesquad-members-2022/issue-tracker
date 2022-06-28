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
    public List<Issue> findByIssueFilter(IssueFilter issueFilter) {
        return jpaQueryFactory
            .select(issue)
            .from(issue)
            .leftJoin(issue.assignees, issueAssignee).fetchJoin()
            .leftJoin(issueAssignee.assignee, member).fetchJoin()
            .leftJoin(issue.labels, issueLabel)
            .leftJoin(issueLabel.label, label)
            .leftJoin(issue.milestone, milestone).fetchJoin()
            .where(
                issuerEq(issueFilter.getIssuerId()),
                issueAssigneeEq(issueFilter.getAssigneeId()),
                issueLabelEq(issueFilter.getLabelId()),
                milestoneEq(issueFilter.getMilestoneId())
            )
            .fetch();
    }

    private BooleanExpression milestoneEq(Long milestoneId) {
        if (milestoneId == null) {
            return null;
        }

        return issue.milestone.id.eq(milestoneId);
    }

    private BooleanExpression issueLabelEq(Long labelId) {
        if (labelId == null) {
            return null;
        }

        return issueLabel.label.id.eq(labelId);
    }

    private BooleanExpression issuerEq(Long issuerId) {
        if (issuerId == null) {
            return null;
        }

        return issue.issuer.id.eq(issuerId);
    }

    private BooleanExpression issueAssigneeEq(Long assigneeId) {
        if (assigneeId == null) {
            return null;
        }

        return issueAssignee.assignee.id.eq(assigneeId);
    }
}
