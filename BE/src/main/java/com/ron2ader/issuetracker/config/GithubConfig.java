package com.ron2ader.issuetracker.config;

import com.ron2ader.issuetracker.auth.github.GithubProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:github.properties")
@EnableConfigurationProperties(GithubProperties.class)
public class GithubConfig {

    private final GithubProperties githubProperties;
}
