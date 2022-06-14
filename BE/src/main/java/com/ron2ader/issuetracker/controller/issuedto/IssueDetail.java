package com.ron2ader.issuetracker.controller.issuedto;

import com.ron2ader.issuetracker.domain.issue.Issue;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IssueDetail {

    private String title;
    private String contents;
    private Boolean openStatus;
    private LocalDateTime createdAt;

    public static IssueDetail from(Issue issue) {
        return new IssueDetail(issue.getTitle(), issue.getContents(), issue.getOpenStatus(), issue.getCreatedAt());
    }
}
