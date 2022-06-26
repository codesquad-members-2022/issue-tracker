package louie.hanse.issuetracker.login.oauth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@RequiredArgsConstructor
@Getter
@ConfigurationProperties(prefix = "oauth")
public class OAuthProperties {

    private final String clientId;
    private final String callbackUrl;
    private final String clientSecret;
    private final String accessTokenApiUrl;
    private final String accessScope;
    private final String loginFormUrl;
    private final String userApiUrl;

}
