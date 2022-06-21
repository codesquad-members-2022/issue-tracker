package com.team33.backend.issue.controller.dto.milestone;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MilestomeEditRequest {

    private String title;
    private String description;
    private LocalDate localDate;

    public MilestomeEditRequest(String title, String description, LocalDate localDate) {
        this.title = title;
        this.description = description;
        this.localDate = localDate;
    }

    public MilestomeEditRequest() {
    }
}
