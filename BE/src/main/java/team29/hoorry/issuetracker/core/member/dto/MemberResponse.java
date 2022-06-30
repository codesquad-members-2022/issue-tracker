package team29.hoorry.issuetracker.core.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team29.hoorry.issuetracker.core.member.domain.Member;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {

	private Long id;
	private String loginId;
	private String profileImageUrl;

	public static MemberResponse from(Member member) {
		return new MemberResponse(member.getId(), member.getLoginId(), member.getProfileImageUrl());
	}
}
