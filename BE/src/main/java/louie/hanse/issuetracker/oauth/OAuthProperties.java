package louie.hanse.issuetracker.oauth;

import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ToString
@ConstructorBinding
@ConfigurationProperties(prefix = "oauth")
public class OAuthProperties {

    public final String clientId;
    public final String callbackUrl;
    public final String clientSecret;
    public final String accessTokenApiUrl;
    public final String accessScope;
    public final String loginFormUrl;
    public final String userApiUrl;

    public OAuthProperties(String clientId, String callbackUrl, String clientSecret,
        String accessTokenApiUrl, String accessScope, String loginFormUrl, String userApiUrl) {
        this.clientId = clientId;
        this.callbackUrl = callbackUrl;
        this.clientSecret = clientSecret;
        this.accessTokenApiUrl = accessTokenApiUrl;
        this.accessScope = accessScope;
        this.loginFormUrl = loginFormUrl;
        this.userApiUrl = userApiUrl;
    }
}
