package com.team33.backend.issue.controller.dto.milestone;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MilestoneCreateRequest {

    private String title;
    private LocalDate deadline;
    private String description;

    public MilestoneCreateRequest(String title, LocalDate deadline, String description) {
        this.title = title;
        this.deadline = deadline;
        this.description = description;
    }

    public MilestoneCreateRequest() {
    }
}
