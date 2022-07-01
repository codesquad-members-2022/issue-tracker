package kr.codesquad.issuetracker.web.dto.issue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import kr.codesquad.issuetracker.domain.Status;
import kr.codesquad.issuetracker.domain.issue.Issue;
import kr.codesquad.issuetracker.web.dto.comment.CommentResponseDto;
import kr.codesquad.issuetracker.web.dto.member.MemberResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@AllArgsConstructor
public class IssueDetailResponse {

	private Long id;

	private String title;

	private Status issueStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime createdDateTime;

	private IssueDetailMemberDto writer;

	private List<IssueAssigneeDto> assignees;

	private List<IssueDetailLabelDto> labels;

	private IssueDetailMilestoneDto milestone;

	private List<CommentResponseDto> comments;

	public static IssueDetailResponse of(Issue issue, IssueDetailMemberDto writer, List<CommentResponseDto> comments,
		List<IssueAssigneeDto> assignees, List<IssueDetailLabelDto> labels,
		IssueDetailMilestoneDto milestone) {
		return new IssueDetailResponse(
			issue.getId(),
			issue.getTitle(),
			issue.getStatus(),
			issue.getCreatedDateTime(),
			writer,
			assignees,
			labels,
			milestone,
			comments
		);
	}
}
