package team24.issuetracker.web.dto.milestonelist;

import java.time.LocalDate;
import lombok.Getter;
import team24.issuetracker.domain.Milestone;

@Getter
public class MilestoneListResponse {

	private Long id;
	private String title;
	private String description;
	private LocalDate dueDate;
	private double progress;
	private int openedIssue;
	private int closedIssue;

	public MilestoneListResponse(Milestone milestone) {
		this.id = milestone.getId();
		this.title = milestone.getTitle();
		this.description = milestone.getDescription();
		this.dueDate = milestone.getDueDate();
//		this.progress = ;
//		this.openedIssue = ;
//		this.closedIssue = ;
	}
}
