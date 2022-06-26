package kr.codesquad.issuetracker.web.dto.label;

import kr.codesquad.issuetracker.domain.label.Color;
import kr.codesquad.issuetracker.domain.label.Label;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
public class LabelDto {

	private Long id;
	private	String title;
	private String description;
	private Color color;

	public static LabelDto from(Label label) {
		return new LabelDto(
			label.getId(),
			label.getTitle(),
			label.getDescription(),
			label.getColor()
		);
	}
}
