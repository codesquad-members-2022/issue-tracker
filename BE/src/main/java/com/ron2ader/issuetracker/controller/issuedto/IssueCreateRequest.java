package com.ron2ader.issuetracker.controller.issuedto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor
@Getter
public class IssueCreateRequest {

    private String title;
    private String contents;
    private List<Long> assigneeIds = new ArrayList<>();
    private List<Long> labelIds = new ArrayList<>();
    private Long milestoneId;

}
