package com.ron2ader.issuetracker.controller.issuedto;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor
public class IssueCreateRequest {

    private String title;
    private String contents;
    private List<Long> assigneeIds;
    private List<Long> labelIds;
    private Long milestoneId;

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public List<Long> getAssigneeIds() {
        if (assigneeIds == null) {
            this.assigneeIds = new ArrayList<>();
        }
        return assigneeIds;
    }

    public List<Long> getLabelIds() {
        if (labelIds == null) {
            this.labelIds = new ArrayList<>();
        }
        return labelIds;
    }

    public Long getMilestoneId() {
        return milestoneId;
    }

}
