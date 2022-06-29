package louie.hanse.issuetracker.web.dto;

import louie.hanse.issuetracker.domain.Issue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class IssueResponse {
    private long issueId;
    private String title;
    private String writerName;
    private String writerImageUrl;
    private LocalDateTime createDateTime;
    private String milestoneTitle;
    private List<LabelSearchResponse> labels;

    public IssueResponse(Issue issue) {
        this.issueId = issue.getId();
        this.title = issue.getTitle();
        this.writerName = issue.getWriter().getSocialId();
        this.writerImageUrl = issue.getWriter().getAvatarImageUrl();
        this.createDateTime = issue.getCreateDateTime();
        this.milestoneTitle = issue.getMilestone().getTitle();
        this.labels = issue.getIssueLabels().stream()
                .map(issueLabel -> issueLabel.getLabel())
                .map(label -> new LabelSearchResponse(label))
                .collect(Collectors.toList());
    }
}
