package kr.codesquad.issuetracker.web.dto.issue;

import kr.codesquad.issuetracker.domain.label.Color;
import kr.codesquad.issuetracker.domain.label.Label;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LabelsResponseDto {

	private Long id;

	private String title;

	private Color color;

	public static LabelsResponseDto from(Label label) {
		return new LabelsResponseDto(
			label.getId(),
			label.getTitle(),
			label.getColor()
		);
	}
}
