package kr.codesquad.issuetracker.web.dto.issue;

import kr.codesquad.issuetracker.domain.label.Color;
import kr.codesquad.issuetracker.domain.label.Label;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IssueDetailLabelDto {

	private String title;

	private Color color;

	public static IssueDetailLabelDto from(Label label) {
		return new IssueDetailLabelDto(
			label.getTitle(),
			label.getColor()
		);
	}
}
