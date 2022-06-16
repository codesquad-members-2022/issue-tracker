package team24.issuetracker.web.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import team24.issuetracker.domain.Assignee;
import team24.issuetracker.domain.Issue;
import team24.issuetracker.domain.IssueLabel;
import team24.issuetracker.domain.Label;
import team24.issuetracker.domain.Milestone;
import team24.issuetracker.domain.User;

@Getter
public class IssueListResponse {

	private final Long id;
	private final String title;
	private final User writer;
	private final LocalDateTime writtenTime;
	private final Milestone milestone;
	private final List<Label> labels;
	private final boolean isClosed;
	private final List<Assignee> assignees;

	public IssueListResponse(Issue issue) {
		this.id = issue.getId();
		this.title = issue.getTitle();
		this.writer = issue.getWriter();
		this.writtenTime = issue.getWrittenTime();
		this.milestone = issue.getMilestone();
		this.labels = List.copyOf(issue.getIssueLabels().stream()
			.map(IssueLabel::getLabel)
			.collect(Collectors.toList()));
		this.isClosed = issue.isClosed();
		this.assignees = List.copyOf(issue.getAssignees());
	}
}
