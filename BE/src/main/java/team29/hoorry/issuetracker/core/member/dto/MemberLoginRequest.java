package team29.hoorry.issuetracker.core.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "멤버 로그인 요청")
public class MemberLoginRequest {

	@Schema(required = true)
	private String loginId;
	@Schema(required = true)
	private String loginPassword;

}
