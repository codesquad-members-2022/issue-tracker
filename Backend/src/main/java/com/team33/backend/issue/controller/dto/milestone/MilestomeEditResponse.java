package com.team33.backend.issue.controller.dto.milestone;

import com.team33.backend.issue.domain.Milestone;
import lombok.Getter;

@Getter
public class MilestomeEditResponse {

    private Long id;
    private String title;
    private String description;

    public MilestomeEditResponse(Milestone milestone) {
        this.id = milestone.getId();
        this.title = milestone.getTitle();
        this.description = milestone.getDescription();
    }

    public MilestomeEditResponse() {
    }
}
