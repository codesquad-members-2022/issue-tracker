package kr.codesquad.issuetracker.dto;

import kr.codesquad.issuetracker.domain.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class IssueDetailResponse {

    private Long id;
    private String title;
    private boolean status;
    private LocalDateTime createdTime;
    private String description;
    private MemberResponse writer;
    private List<AssigneeResponse> assignees;
    private List<CommentResponse> comments;
    private List<LabelResponse> labels;
    private MilestoneResponse milestone;

    @Builder
    public IssueDetailResponse(Issue issue, MemberResponse writer, List<AssigneeResponse> assignees, List<CommentResponse> comments, List<LabelResponse> labels, MilestoneResponse milestone) {
        this.id = issue.getId();
        this.title = issue.getTitle();
        this.status = issue.isStatus();
        this.createdTime = issue.getCreatedTime();
        this.description = issue.getContent();
        this.writer = writer;
        this.assignees = assignees;
        this.comments = comments;
        this.labels = labels;
        this.milestone = milestone;
    }
}
