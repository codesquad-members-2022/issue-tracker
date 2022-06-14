package kr.codesquad.issuetracker.auth;

import java.util.NoSuchElementException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LoginService {

    private final String clientId = "2f2f8ccb4f67b255a805";
    private final String clientSecret = "43aeaaa8c22747b989434e5d400aa7514eedd55a";
    private static final String GITHUB_AUTHORIZATION_SERVER_URL = "https://github.com/login/oauth/access_token";

    public AccessToken getAccessToken(String code) {
        WebClient webClient = WebClient.create();
        AccessTokenRequestBody accessTokenRequestBody = new AccessTokenRequestBody(clientId,
            clientSecret, code);

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
