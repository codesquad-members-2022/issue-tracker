package com.ron2ader.issuetracker.controller.issuedto;

import com.ron2ader.issuetracker.domain.label.Label;
import com.ron2ader.issuetracker.domain.member.Member;
import com.ron2ader.issuetracker.domain.milestone.Milestone;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IssueFilter {

    private final Boolean openStatus;
    private final Long issuerId;
    private final Long labelId;
    private final Long milestoneId;
    private final Long assigneeId;

    public static IssueFilter from(IssueCondition issueCondition) {
        return new IssueFilter(issueCondition.getOpenStatus(),
                issueCondition.getIssuerId(),
                issueCondition.getLabelId(),
                issueCondition.getMilestoneId(),
                issueCondition.getAssigneeId());
    }

}
