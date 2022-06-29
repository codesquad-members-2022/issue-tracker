package com.ron2ader.issuetracker.controller.issuedto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class IssueCreateRequest {

    private String title;
    private String contents;
    private List<Long> assigneeIds = new ArrayList<>();
    private List<Long> labelIds = new ArrayList<>();
    private Long milestoneId;

}
