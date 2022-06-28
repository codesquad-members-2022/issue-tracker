package kr.codesquad.issuetracker.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MilestoneRequest {

    private String title;
    private String description;
    private LocalDate deadline;

    @Builder
    public MilestoneRequest(String title, String description, LocalDate deadline) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
    }
}
