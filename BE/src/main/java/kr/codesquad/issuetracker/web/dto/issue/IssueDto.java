package kr.codesquad.issuetracker.web.dto.issue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import kr.codesquad.issuetracker.domain.Status;
import kr.codesquad.issuetracker.domain.issue.Issue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@AllArgsConstructor
public class IssueDto {

	private Long id;

	private String title;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime createdTime;

	private String writer;

	private List<IssueLabelDto> labels;

	private String milestoneName;

	private Status status;

	public static IssueDto from(Issue issue) {
		return new IssueDto(
			issue.getId(),
			issue.getTitle(),
			issue.getCreatedDateTime(),
			issue.getWriter().getName(),
			issue.getLabels().stream()
				.map(IssueLabelDto::from)
				.collect(Collectors.toList()),
			issue.getMilestone().getTitle(),
			issue.getStatus()
		);
	}
}
