package kr.codesquad.issuetracker.web.dto.issue;

import java.time.LocalDate;
import kr.codesquad.issuetracker.domain.milestone.Milestone;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MilestonesResponseDto {

	private Long id;

	private String title;

	private LocalDate deadLine;

	public static MilestonesResponseDto from(Milestone milestone) {
		return new MilestonesResponseDto(
			milestone.getId(),
			milestone.getTitle(),
			milestone.getDeadLine()
		);
	}
}
