package com.team33.backend.issue.controller.dto.issue;

import com.team33.backend.issue.controller.dto.MemberResponse;
import com.team33.backend.issue.controller.dto.label.LabelResponse;
import com.team33.backend.issue.controller.dto.milestone.MilestoneResponse;
import com.team33.backend.issue.domain.Issue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class IssueResponse {

    private final long id;
    private final String title;
    private final LocalDateTime createdAt;
    private final MemberResponse author;
    private final MilestoneResponse milestone;
    private final List<LabelResponse> labels;
    private final List<MemberResponse> assignees;

    public IssueResponse(Issue issue, List<LabelResponse> labels, List<MemberResponse> assignees) {
        this.id = issue.getId();
        this.title = issue.getTitle();
        this.author = MemberResponse.from(issue.getAuthor());
        this.createdAt = issue.getCreatedAt();
        this.milestone = (isMilestoneExists(issue)) ? new MilestoneResponse(issue.getMilestone()) : null;
        this.labels = labels;
        this.assignees = assignees;
    }

    private boolean isMilestoneExists(Issue issue) {
        return issue.getMilestone() != null;
    }

    @Override
    public String toString() {
        return "IssueResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createdAt=" + createdAt +
                ", author=" + author +
                ", milestone=" + milestone +
                ", labels=" + labels +
                ", assignees=" + assignees +
                '}';
    }
}
