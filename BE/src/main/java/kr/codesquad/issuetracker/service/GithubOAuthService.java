package kr.codesquad.issuetracker.service;

import kr.codesquad.issuetracker.dto.GitHubUser;
import kr.codesquad.issuetracker.dto.TokenRequest;
import kr.codesquad.issuetracker.dto.TokenRespond;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GithubOAuthService {

    private final WebClient webClient = WebClient.create();

    @Value("${oauth.client-id}")
    private String CLIENT_ID;

    @Value("${oauth.client-secret}")
    private String CLIENT_SECRET;

    @Value("${oauth.access-token-uri}")
    private String ACCESS_TOKEN_URI;

    @Value("${oauth.user-uri}")
    private String USER_URI;

    public String getAccessToken(String code){
        TokenRespond tokenRespond = issueAccessToken(code);
        return tokenRespond.getAccessToken();
    }

    private TokenRespond issueAccessToken(String code){
        TokenRequest tokenRequest = TokenRequest.builder()
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .code(code)
                .build();

        return webClient.post()
                .uri(ACCESS_TOKEN_URI)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(tokenRequest)
                .retrieve()
                .bodyToMono(TokenRespond.class).blockOptional()
                .orElseThrow();
    }

    public GitHubUser getGitHubUser(String accessToken){
        return webClient.get()
                .uri(USER_URI)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION,"token " +accessToken)
                .retrieve()
                .bodyToMono(GitHubUser.class)
                .blockOptional()
                .orElseThrow(RuntimeException::new);
    }
}
