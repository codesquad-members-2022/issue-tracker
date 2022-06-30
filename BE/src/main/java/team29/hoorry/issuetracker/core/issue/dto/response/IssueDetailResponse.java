package team29.hoorry.issuetracker.core.issue.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team29.hoorry.issuetracker.core.issue.domain.Status;
import team29.hoorry.issuetracker.core.label.dto.LabelResponse;
import team29.hoorry.issuetracker.core.member.dto.MemberResponse;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IssueDetailResponse {

	private Long id;
	private String title;
	private Status status;
	private List<LabelResponse> labels;
	private List<MemberResponse> assignees;
	private MemberResponse writer;
	private IssueMilestoneResponse milestone;
	private List<CommentResponse> comments;

}
