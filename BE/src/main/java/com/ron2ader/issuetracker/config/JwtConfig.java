package com.ron2ader.issuetracker.config;

import com.ron2ader.issuetracker.auth.jwt.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:auth.yml")
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfig {

    private final JwtProperties jwtProperties;
}
