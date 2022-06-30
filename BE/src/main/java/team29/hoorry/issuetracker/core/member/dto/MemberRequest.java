package team29.hoorry.issuetracker.core.member.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team29.hoorry.issuetracker.core.member.domain.Member;

@Getter
@NoArgsConstructor
@Schema(description = "멤버 회원가입 요청")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberRequest {

	public static final String DEFAULT_IMAGE = "https://avatars.githubusercontent.com/u/81368630?v=4";

	@Schema(required = true)
	private String loginId;
	@Schema(required = true)
	private String loginPassword;
	@Schema(required = true)
	private String name;
	@Schema(required = true)
	private String email;

	private String profileImageUrl = DEFAULT_IMAGE;

	private Long oauthId;

	public static Member toEntity(MemberRequest memberRequest) {
		return Member.of(
			memberRequest.loginId,
			memberRequest.loginPassword,
			memberRequest.name,
			memberRequest.email,
			memberRequest.profileImageUrl,
			memberRequest.oauthId
		);
	}
}
