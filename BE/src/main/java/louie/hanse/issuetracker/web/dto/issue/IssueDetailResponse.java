package louie.hanse.issuetracker.web.dto.issue;

import louie.hanse.issuetracker.domain.Issue;
import louie.hanse.issuetracker.domain.IssueLabel;
import louie.hanse.issuetracker.domain.Member;
import louie.hanse.issuetracker.domain.Status;
import louie.hanse.issuetracker.web.dto.label.LabelResponse;
import louie.hanse.issuetracker.web.dto.manager.ManagerResponse;
import louie.hanse.issuetracker.web.dto.comment.CommentResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class IssueDetailResponse {
    private Long issueId;
    private String title;
    private String writerName;
    private String userImageUrl;
    private LocalDateTime createdDataTime;
    private Status status;
    private long commentCount;
    private List<CommentResponse> comments;
    private List<ManagerResponse> managers;
    private String milestoneName;
    private double milestoneProgress;
    private List<LabelResponse> labels;

    public IssueDetailResponse(Issue issue, Member user) {
        this.issueId = issue.getId();
        this.title = issue.getTitle();
        this.writerName = issue.getWriter().getSocialId();
        this.userImageUrl = user.getAvatarImageUrl();
        this.createdDataTime = issue.getCreateDateTime();
        this.status = issue.getStatus();
        this.commentCount = issue.getComments().size();
        this.comments = issue.getComments()
                .stream()
                .map(comment -> new CommentResponse(comment, user))
                .collect(Collectors.toList());
        this.managers = issue.getIssueManagers()
                .stream()
                .map(issueManager -> new ManagerResponse(issueManager.getManager()))
                .collect(Collectors.toList());
        this.milestoneName = issue.getMilestone().getTitle();
        this.milestoneProgress = calculateMilestoneProgress(issue);
        this.labels = issue.getIssueLabels()
                .stream().
                map(IssueLabel::getLabel)
                .map(LabelResponse::new)
                .collect(Collectors.toList());
    }

    private double calculateMilestoneProgress(Issue issue) {
        int totalIssueCount = issue.getMilestone().getIssues().size();
        long closedIssueCount = issue.getMilestone().getIssues()
                .stream()
                .filter(Issue::isClosed)
                .count();

        return (double) closedIssueCount / totalIssueCount;
    }
}
