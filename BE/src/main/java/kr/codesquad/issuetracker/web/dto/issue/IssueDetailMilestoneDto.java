package kr.codesquad.issuetracker.web.dto.issue;

import java.time.LocalDate;
import kr.codesquad.issuetracker.domain.label.Label;
import kr.codesquad.issuetracker.domain.milestone.Milestone;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IssueDetailMilestoneDto {

	private String title;

	private LocalDate deadLine;

	public static IssueDetailMilestoneDto from(Milestone milestone) {
		return new IssueDetailMilestoneDto(
			milestone.getTitle(),
			milestone.getDeadLine()
		);
	}
}
