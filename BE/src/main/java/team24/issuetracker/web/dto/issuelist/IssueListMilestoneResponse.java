package team24.issuetracker.web.dto.issuelist;

import lombok.Getter;
import team24.issuetracker.domain.Milestone;

@Getter
public class IssueListMilestoneResponse {

	private Long id;
	private String title;

	public IssueListMilestoneResponse(Milestone milestone) {
		this.id = milestone.getId();
		this.title = milestone.getTitle();
	}
}
