package kr.codesquad.issuetracker.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccessTokenRequestDto {
	private String clientId;
	private String clientSecret;
	private String code;

	public AccessTokenRequestDto(String clientId, String clientSecret, String code) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.code = code;
	}
}
