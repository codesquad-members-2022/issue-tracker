package team24.issuetracker.web.dto;

import lombok.Getter;
import team24.issuetracker.domain.Label;

@Getter
public class IssueListLabelResponse {

	private Long id;
	private String title;

	public IssueListLabelResponse(Label label) {
		this.id = label.getId();
		this.title = label.getTitle();
	}

}
