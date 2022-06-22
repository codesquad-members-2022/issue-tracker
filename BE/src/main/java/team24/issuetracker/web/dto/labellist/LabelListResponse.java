package team24.issuetracker.web.dto.labellist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team24.issuetracker.domain.Label;

@Getter
public class LabelListResponse {

	private Long id;
	private String title;
	private String description;

	public LabelListResponse(Label label) {
		this.id = label.getId();
		this.title = label.getTitle();
		this.description = label.getDescription();
	}
}
