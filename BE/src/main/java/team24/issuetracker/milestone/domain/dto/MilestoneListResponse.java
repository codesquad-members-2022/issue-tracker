package team24.issuetracker.milestone.domain.dto;

import java.time.LocalDate;
import lombok.Getter;
import team24.issuetracker.milestone.domain.Milestone;

@Getter
public class MilestoneListResponse {

	private final Long id;
	private final String title;
	private final String description;
	private final LocalDate dueDate;
	private final double progress;
	private final Long openedIssue;
	private final Long closedIssue;

	public MilestoneListResponse(Milestone milestone, long totalNumber, long openedIssueNumber) {
		this.id = milestone.getId();
		this.title = milestone.getTitle();
		this.description = milestone.getDescription();
		this.dueDate = milestone.getDueDate();
		this.openedIssue = openedIssueNumber;
		this.closedIssue = calculateClosedIssue(totalNumber, openedIssueNumber);
		this.progress = calculateProgress(totalNumber, openedIssueNumber);
	}

	private double calculateProgress(long totalNumber, long openedIssueNumber) {
		return (double)openedIssueNumber / totalNumber;
	}

	private long calculateClosedIssue(long totalNumber, long openedIssueNumber) {
		return totalNumber - openedIssueNumber;
	}
}
