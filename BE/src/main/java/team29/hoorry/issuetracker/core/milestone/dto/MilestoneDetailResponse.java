package team29.hoorry.issuetracker.core.milestone.dto;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter(AccessLevel.PRIVATE)
public class MilestoneDetailResponse {

	private Long id;
	private String title;
	private String description;
	private LocalDateTime dueDate;
	private Integer openIssueCount;
	private Integer closedIssueCount;

	public static MilestoneDetailResponse of(Long id, String title, String description,
		LocalDateTime dueDate, Integer openIssueCount, Integer closedIssueCount) {
		return new MilestoneDetailResponse(id, title, description, dueDate, openIssueCount, closedIssueCount);
	}
}
