package louie.hanse.issuetracker.web.dto.milestone;

import lombok.Getter;
import louie.hanse.issuetracker.domain.Issue;
import louie.hanse.issuetracker.domain.Milestone;

import java.time.LocalDate;

@Getter
public class MilestoneDetailResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDate completedDate;
    private long openedIssueCount;
    private long closedIssueCount;

    public MilestoneDetailResponse(Milestone milestone) {
        this.id = milestone.getId();
        this.title = milestone.getTitle();
        this.description = milestone.getDescription();
        this.completedDate = milestone.getCompletedDate();
        this.openedIssueCount = milestone.getIssues().stream()
                .filter(Issue::isOpened)
                .count();
        this.closedIssueCount = milestone.getIssues().stream()
                .filter(Issue::isClosed)
                .count();
    }
}
