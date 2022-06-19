package kr.codesquad.issuetracker.web.dto.user;

import kr.codesquad.issuetracker.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class UserResponseDto {

	private final String name;
	private final String email;
	private final String avatarUrl;
	private final String token;

	public static UserResponseDto of(Member member, String token) {
		return new UserResponseDto(
			member.getName(),
			member.getEmail(),
			member.getImageUrl(),
			token
		);
	}
}
