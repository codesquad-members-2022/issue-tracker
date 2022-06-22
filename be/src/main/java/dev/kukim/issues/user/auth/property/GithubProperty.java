package dev.kukim.issues.user.auth.property;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
@ConfigurationProperties("oauth.github")
@ConstructorBinding
public class GithubProperty {

	@NotEmpty
	private final String accessTokenUri;

	@NotEmpty
	private final String userInfoUri;

	@NotEmpty
	private final String clientId;

	@NotEmpty
	private final String clientSecret;

	public GithubProperty(String accessTokenUri, String userInfoUri, String clientId,
		String clientSecret) {
		this.accessTokenUri = accessTokenUri;
		this.userInfoUri = userInfoUri;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}
}
