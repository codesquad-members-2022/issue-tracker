package kr.codesquad.issuetracker.web.dto.member;

import kr.codesquad.issuetracker.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class MemberResponseDto {

	private final String name;
	private final String email;
	private final String avatarUrl;
	private final String token;

	public static MemberResponseDto of(Member member, String token) {
		return new MemberResponseDto(
			member.getName(),
			member.getEmail(),
			member.getImageUrl(),
			token
		);
	}
}
