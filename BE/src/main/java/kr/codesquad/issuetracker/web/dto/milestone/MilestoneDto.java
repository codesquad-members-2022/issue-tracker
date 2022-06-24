package kr.codesquad.issuetracker.web.dto.milestone;

import java.time.LocalDate;
import java.time.LocalDateTime;
import kr.codesquad.issuetracker.domain.milestone.Milestone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@AllArgsConstructor
public class MilestoneDto {

	private Long id;

	private String title;

	private String description;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime createdDateTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime modifiedDateTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDate deadLine;

	private int openedIssue;

	private int closedIssue;

	public static MilestoneDto of(Milestone milestone) {
		return new MilestoneDto(
			milestone.getId(),
			milestone.getTitle(),
			milestone.getDescription(),
			milestone.getCreatedDateTime(),
			milestone.getModifiedDateTime(),
			milestone.getDeadLine(),
			milestone.countOpenIssue(),
			milestone.countClosedIssue()
		);
	}
}
