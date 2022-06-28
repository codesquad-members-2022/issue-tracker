package com.ron2ader.issuetracker.controller.issuedto;

import com.ron2ader.issuetracker.domain.label.Label;
import com.ron2ader.issuetracker.domain.member.Member;
import com.ron2ader.issuetracker.domain.milestone.Milestone;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IssueFilter {

    private Boolean openStatus;
    private Member assignee;
    private Label label;
    private Milestone milestone;
    private Member issuer;

    public static IssueFilter from(IssueCondition issueCondition) {
        return new IssueFilter(issueCondition.getOpenStatus(), issueCondition.getAssignee(), issueCondition.getLabel(),
            issueCondition.getMilestone(), issueCondition.getIssuer());
    }
}
