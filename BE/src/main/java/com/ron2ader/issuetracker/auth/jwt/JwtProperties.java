package com.ron2ader.issuetracker.auth.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String subjectAccess;
    private String subjectRefresh;
    private String issuer;
}
