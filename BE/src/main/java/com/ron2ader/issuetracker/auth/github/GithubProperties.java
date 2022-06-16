package com.ron2ader.issuetracker.auth.github;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "github")
public class GithubProperties {

    private String clientId;
    private String clientSecret;
}
