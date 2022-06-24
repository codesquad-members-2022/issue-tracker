package be.codesquad.issuetracker.oauth.service;

import be.codesquad.issuetracker.oauth.dto.GithubUser;
import be.codesquad.issuetracker.oauth.dto.TokenInformation;
import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AuthService {

    private final String authPath;
    private final String resourcePath;
    private final String clientId;
    private final String clientSecret;

    public AuthService(
        @Value("${oauth.github.auth-path}") String authPath,
        @Value("${oauth.github.resource-path}") String resourcePath,
        @Value("${oauth.github.client-id}") String clientId,
        @Value("${oauth.github.client-secret}") String clientSecret) {
        this.authPath = authPath;
        this.resourcePath = resourcePath;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public TokenInformation getToken(String code) {
        return WebClient.create()
            .post()
            .uri(URI.create(authPath))
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(getBodyValue(code))
            .retrieve()
            .bodyToMono(TokenInformation.class)
            .block();
    }

    public GithubUser getUser(String accessToken) {
        return WebClient.create()
            .get()
            .uri(URI.create(resourcePath))
            .header(HttpHeaders.ACCEPT, "application/vnd.github.v3+json")
            .header(HttpHeaders.AUTHORIZATION, "token " + accessToken)
            .retrieve()
            .bodyToMono(GithubUser.class)
            .block();
    }

    public GithubUser getGithubUser(String code) {
        TokenInformation token = getToken(code);
        return getUser(token.getAccessToken());
    }

    private MultiValueMap<String, Object> getBodyValue(String code) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("code", code);
        return body;
    }
}
