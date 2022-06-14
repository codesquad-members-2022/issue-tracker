package kr.codesquad.issuetracker.auth;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final GitHubOAuthProperties gitHubOAuthProperties;

    private static final String GITHUB_AUTHORIZATION_SERVER_URL = "https://github.com/login/oauth/access_token";
    private static final String GITHUB_RESOURCE_SERVER_API_URL = "https://api.github.com/user";

    public AccessToken getAccessToken(String code) {
        WebClient webClient = WebClient.create();
        AccessTokenRequestBody accessTokenRequestBody = new AccessTokenRequestBody(gitHubOAuthProperties.getClientId(),
            gitHubOAuthProperties.getClientSecret(), code);

        return webClient.post()
            .uri(GITHUB_AUTHORIZATION_SERVER_URL)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(accessTokenRequestBody)
            .retrieve()
            .bodyToMono(AccessToken.class)
            .blockOptional()
            .orElseThrow(NoSuchElementException::new);
    }

    public GitHubUserInfo getGitHubUserInfo(AccessToken accessToken) {
        WebClient webClient = WebClient.create();
        return webClient.get()
            .uri(GITHUB_RESOURCE_SERVER_API_URL)
            .accept(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, "token " + accessToken.getAccessToken())
            .retrieve()
            .bodyToMono(GitHubUserInfo.class)
            .blockOptional()
            .orElseThrow(NoSuchElementException::new);
    }
}
