package team24.issuetracker.oauth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import team24.issuetracker.oauth.properties.OAuthProperties;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GitHubAccessTokenRequest {

	private final String clientId;
	private final String clientSecret;
	private final String code;

	public GitHubAccessTokenRequest(OAuthProperties oAuthProperties, String code) {
		this.clientId = oAuthProperties.getClientId();
		this.clientSecret = oAuthProperties.getClientSecret();
		this.code = code;
	}
}
