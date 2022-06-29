package kr.codesquad.issuetracker.web.dto.issue;

import java.util.List;
import kr.codesquad.issuetracker.domain.issue.Issue;
import kr.codesquad.issuetracker.domain.label.Label;
import kr.codesquad.issuetracker.domain.member.Member;
import kr.codesquad.issuetracker.domain.milestone.Milestone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssueAddRequestDto {

	private Long memberId;

	private String title;

	private String description;

	private List<Long> assignees;

	private List<Long> labels;

	private Long milestoneId;
}
