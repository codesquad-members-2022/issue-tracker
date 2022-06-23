package team24.issuetracker.oauth.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@RequiredArgsConstructor
public class GitHubOAuthProperties {

	private final String clientId;

	private final String clientSecret;

	private final String accessTokenUrl;

	private final String userInfoUrl;

	private final String redirectUrl;

	private final String userEmailInfoUrl;

	private final String codeRequestUrl;
}
