package kr.codesquad.issuetracker.web.dto.label;

import kr.codesquad.issuetracker.domain.label.Color;
import kr.codesquad.issuetracker.domain.label.Label;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LabelRequestDto {

	private String title;
	private String description;
	private Color color;

	public Label toEntity() {
		return new Label(
			title,
			description,
			color
		);
	}
}
