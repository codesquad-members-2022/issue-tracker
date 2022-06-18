package com.ron2ader.issuetracker.auth.github;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "github")
public class GithubProperties {

    private final String clientId;
    private final String clientSecret;
    private final String accessTokenUrl;
}
