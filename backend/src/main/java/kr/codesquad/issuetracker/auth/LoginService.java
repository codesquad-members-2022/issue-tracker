package kr.codesquad.issuetracker.auth;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class LoginService {

    private static final String GITHUB_AUTHORIZATION_SERVER_URL = "https://github.com/login/oauth/access_token";
    private final GitHubOAuthProperties gitHubOAuthProperties;

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

}
