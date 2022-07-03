package team24.issuetracker.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team24.issuetracker.member.domain.Member;

@Getter
@AllArgsConstructor
public class LoginMemberResponse {

	private final Long id;
	private final String name;
	private final String profileImage;
	private final String accessToken;
	private final String refreshToken;

	public static LoginMemberResponse  of(Member member) {
		return new LoginMemberResponse(member.getId(), member.getName(), member.getProfileImage(),
			member.getAccessToken(), member.getRefreshToken());
	}
}

