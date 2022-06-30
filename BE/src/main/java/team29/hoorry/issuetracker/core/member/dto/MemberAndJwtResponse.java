package team29.hoorry.issuetracker.core.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team29.hoorry.issuetracker.core.jwt.dto.JwtResponse;

@Getter
@AllArgsConstructor
public class MemberAndJwtResponse {

	private MemberResponse memberResponse;
	private JwtResponse jwtResponse;

}
