package kr.codesquad.issuetracker.web.dto.issue;

import kr.codesquad.issuetracker.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IssueDetailMemberDto {

	private String name;

	private String email;

	private String avatarUrl;

	public static IssueDetailMemberDto from(Member member) {
		return new IssueDetailMemberDto(
			member.getName(),
			member.getEmail(),
			member.getImageUrl()
		);
	}
}
