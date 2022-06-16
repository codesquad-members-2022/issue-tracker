package com.ron2ader.issuetracker.service;

import com.ron2ader.issuetracker.auth.github.GithubProperties;
import com.ron2ader.issuetracker.auth.github.GithubToken;
import com.ron2ader.issuetracker.auth.github.GithubTokenRequest;
import com.ron2ader.issuetracker.auth.github.GithubUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class GithubOAuthService {

    public static final String ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
    private final WebClient webClient;
    private final GithubProperties githubProperties;

    public GithubToken requestAccessToken(String code) {
        return webClient.post()
            .uri(ACCESS_TOKEN_URL)
            .bodyValue(
                GithubTokenRequest.of(githubProperties.getClientId(), githubProperties.getClientSecret(), code)
            )
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(GithubToken.class)
            .block();
    }

    public GithubUserInfo requestUserInfo(GithubToken githubToken) {
        return webClient.get()
            .uri("/user")
            .header(HttpHeaders.AUTHORIZATION, githubToken.toHeader())
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(GithubUserInfo.class)
            .block();
    }
}
