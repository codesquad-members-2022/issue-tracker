package com.ron2ader.issuetracker.controller.issuedto;

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
