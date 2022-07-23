package louie.hanse.issuetracker.web.dto.issue;

import lombok.Getter;
import louie.hanse.issuetracker.domain.Issue;
import louie.hanse.issuetracker.domain.IssueLabel;
import louie.hanse.issuetracker.web.dto.label.LabelResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class IssueResponse {
    private long issueId;
    private String title;
    private String writerName;
    private String writerImageUrl;
    private LocalDateTime createDateTime;
    private String milestoneTitle;
    private List<LabelResponse> labels;

    public IssueResponse(Issue issue) {
        this.issueId = issue.getId();
        this.title = issue.getTitle();
        this.writerName = issue.getWriter().getSocialId();
        this.writerImageUrl = issue.getWriter().getAvatarImageUrl();
        this.createDateTime = issue.getCreateDateTime();
        this.milestoneTitle = issue.getMilestone().getTitle();
        this.labels = issue.getIssueLabels().stream()
                .map(IssueLabel::getLabel)
                .map(LabelResponse::new)
                .collect(Collectors.toList());
    }
}
