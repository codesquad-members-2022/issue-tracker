package com.team33.backend.issue.controller.dto.milestone;

import com.team33.backend.issue.domain.Milestone;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MilestoneResponse {

    private Long id;
    private String title;
    private LocalDate deadline;

    public MilestoneResponse(Milestone milestone) {
        this.id = milestone.getId();
        this.title = milestone.getTitle();
        this.deadline = milestone.getDeadline();
    }

    protected MilestoneResponse() {
    }
}
