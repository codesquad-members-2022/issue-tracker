package kr.codesquad.issuetracker.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MilestoneRequest {

    private String title;
    private String description;
    private LocalDate deadline;

}
