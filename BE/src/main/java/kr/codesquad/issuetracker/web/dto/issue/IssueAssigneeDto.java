package kr.codesquad.issuetracker.web.dto.issue;

import kr.codesquad.issuetracker.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IssueAssigneeDto {

	private Long id;
	private String image;
	private String userName;


	public static IssueAssigneeDto from(Member assignee) {
		return new IssueAssigneeDto(
			assignee.getId(),
			assignee.getImageUrl(),
			assignee.getName()
		);
	}
}
