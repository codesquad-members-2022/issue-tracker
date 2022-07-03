package team24.issuetracker.issue.domain.dto;

import lombok.Getter;
import team24.issuetracker.member.domain.Member;

@Getter
public class IssueListMemberResponse {

	private final Long id;
	private final String name;
	private final String email;
	private final String profileImage;

	public IssueListMemberResponse(Member member) {
		this.id = member.getId();
		this.name = member.getName();
		this.email = member.getEmail();
		this.profileImage = member.getProfileImage();
	}
}
