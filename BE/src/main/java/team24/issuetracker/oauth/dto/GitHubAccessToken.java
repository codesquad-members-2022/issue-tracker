package team24.issuetracker.oauth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GitHubAccessToken {

	private String accessToken;
	private String tokenType;
	private String scope;

	public String fullInfo() {
		return tokenType + " " + accessToken;
	}
}
