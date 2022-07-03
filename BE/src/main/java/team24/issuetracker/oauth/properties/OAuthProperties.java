package team24.issuetracker.oauth.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@ConstructorBinding
@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "github")
public class OAuthProperties {

	private final String clientId;
	private final String clientSecret;
	private final String accessTokenUri;
	private final String userInfoUri;
	private final String userEmailInfoUri;
	private final String codeRequestUri;
}
