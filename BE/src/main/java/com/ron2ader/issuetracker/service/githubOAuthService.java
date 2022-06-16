package com.ron2ader.issuetracker.service;

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
public class githubOAuthService {

    public static final String ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
    private final WebClient webClient;
    private String clientId;
    private String clientSecret;


    public GithubToken requestAccessToken(String code) {
        return webClient.post()
            .uri(ACCESS_TOKEN_URL)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .bodyValue(
                GithubTokenRequest.of(clientId, clientSecret, code)
            )
            .retrieve()
            .bodyToMono(GithubToken.class)
            .block();
    }

    public GithubUserInfo requestUserInfo(GithubToken githubToken) {
        return webClient.get()
            .uri("/user")
            .header(HttpHeaders.AUTHORIZATION, githubToken.toHeader())
            .retrieve()
            .bodyToMono(GithubUserInfo.class)
            .block();
    }
}
