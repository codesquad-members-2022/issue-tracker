package com.ron2ader.issuetracker.controller.issuedto;

import com.ron2ader.issuetracker.domain.label.Label;
import com.ron2ader.issuetracker.domain.member.Member;
import com.ron2ader.issuetracker.domain.milestone.Milestone;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class IssueCondition {

    private Boolean openStatus;
    private Long issuerId;
    private Long labelId;
    private Long milestoneId;
    private Long assigneeId;

}
