package kr.codesquad.issuetracker.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "oauth2.github")
@Getter
@Setter
public class GitHubOAuthProperties {

    private String clientId;
    private String clientSecret;

}
