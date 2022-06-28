package codesquad.issuetracker.dto.milestone;

import codesquad.issuetracker.domain.Issue;
import codesquad.issuetracker.domain.IssueStatus;
import codesquad.issuetracker.domain.Milestone;
import codesquad.issuetracker.domain.MilestoneStatus;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MilestoneDto {

    private Long id;
    private String subject;
    private String description;
    private LocalDate endDate;
    private MilestoneStatus status;
    private Long totalCountOfIssues;
    private Long countOfClosedIssues;

    private MilestoneDto() {}

    public static MilestoneDto from(Milestone milestone) {
        List<Issue> issues = milestone.getIssues();
        long totalCountOfIssues = issues.size(), countOfClosedIssues = issues.stream().filter(issue -> issue.hasSameStatus(IssueStatus.CLOSED)).count();

        return new MilestoneDto(milestone.getId(), milestone.getSubject(),
            milestone.getDescription(), milestone.getEndDate(), milestone.getStatus(),
            totalCountOfIssues, countOfClosedIssues);
    }
}
