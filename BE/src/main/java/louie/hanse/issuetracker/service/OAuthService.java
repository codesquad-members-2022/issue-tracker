package louie.hanse.issuetracker.service;

import lombok.RequiredArgsConstructor;
import louie.hanse.issuetracker.login.oauth.GithubAccessToken;
import louie.hanse.issuetracker.login.oauth.GithubAccessTokenRequest;
import louie.hanse.issuetracker.login.oauth.GithubUser;
import louie.hanse.issuetracker.login.oauth.OAuthProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class OAuthService {
    
    private final OAuthProperties oAuthProperties;

    public GithubAccessToken getAccessToken(String code) {
        GithubAccessTokenRequest accessTokenRequest = new GithubAccessTokenRequest(
            oAuthProperties.getClientId(), oAuthProperties.getClientSecret(), code);

        return WebClient.create()
            .post().uri(oAuthProperties.getAccessTokenApiUrl())
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(accessTokenRequest)
            .retrieve()
            .bodyToMono(GithubAccessToken.class)
            .block();
    }

    public GithubUser getUserInfo(GithubAccessToken githubAccessToken) {
        return WebClient.create()
            .get().uri(oAuthProperties.getUserApiUrl())
            .accept(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, githubAccessToken.getAuthorizationValue())
            .retrieve()
            .bodyToMono(GithubUser.class)
            .block();
    }
}
