package com.ron2ader.issuetracker.controller.issuedto;

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
