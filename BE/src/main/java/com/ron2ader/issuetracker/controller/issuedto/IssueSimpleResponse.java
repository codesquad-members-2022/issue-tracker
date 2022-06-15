package com.ron2ader.issuetracker.controller.issuedto;

import com.ron2ader.issuetracker.controller.memberdto.MemberDto;
import com.ron2ader.issuetracker.domain.issue.Issue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class IssueSimpleResponse {

    private MemberDto memberDto;
    private Long issueNumber;
    private String title;
    private LocalDateTime createdAt;

    public static IssueSimpleResponse from(Issue issue) {
        return new IssueSimpleResponse(MemberDto.from(issue.getIssuer()), issue.getId(),
                issue.getTitle(), issue.getCreatedAt());
    }
}
