package com.team33.backend.issue.controller.dto.milestone;

import com.team33.backend.issue.domain.Milestone;
import lombok.Getter;

@Getter
public class MilestoneCreateResponse {

    private Long id;
    private String title;

    public MilestoneCreateResponse(Milestone milestone) {
        this.id = milestone.getId();
        this.title = milestone.getTitle();
    }

    public MilestoneCreateResponse() {
    }
}
