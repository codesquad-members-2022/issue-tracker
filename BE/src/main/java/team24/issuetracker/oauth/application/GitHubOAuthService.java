package team24.issuetracker.oauth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team24.issuetracker.oauth.dto.OAuthAccessToken;
import team24.issuetracker.oauth.properties.GitHubOAuthProperties;

@Service("github")
@RequiredArgsConstructor
public class GitHubOAuthService {

	private final GitHubOAuthProperties gitHubOAuthProperties;

	public OAuthAccessToken obtainAccessToken(String code) {

		return WebClient.create().post()
			.uri(gitHubOAuthProperties.getAccessTokenUrl())
	}
}
