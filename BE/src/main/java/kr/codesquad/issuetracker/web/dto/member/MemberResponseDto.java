package kr.codesquad.issuetracker.web.dto.member;

import kr.codesquad.issuetracker.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class MemberResponseDto {

	private String name;
	private String email;
	private String avatarUrl;
	private String token;

	public static MemberResponseDto of(Member member, String token) {
		return new MemberResponseDto(
			member.getName(),
			member.getEmail(),
			member.getImageUrl(),
			token
		);
	}
}
