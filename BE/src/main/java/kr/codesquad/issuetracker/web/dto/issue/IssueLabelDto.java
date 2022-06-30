package kr.codesquad.issuetracker.web.dto.issue;

import kr.codesquad.issuetracker.domain.label.Color;
import kr.codesquad.issuetracker.domain.label.Label;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IssueLabelDto {

	private String name;

	private Color color;

	public static IssueLabelDto from(Label label) {
		return new IssueLabelDto(
			label.getTitle(),
			label.getColor()
		);
	}
}
