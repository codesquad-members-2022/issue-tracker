package team24.issuetracker.web.dto.labellist;

import lombok.Getter;
import team24.issuetracker.domain.Label;

@Getter
public class LabelListResponse {

	private final Long id;
	private final String title;
	private final String description;

	public LabelListResponse(Label label) {
		this.id = label.getId();
		this.title = label.getTitle();
		this.description = label.getDescription();
	}
}
