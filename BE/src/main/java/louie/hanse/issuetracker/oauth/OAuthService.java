package louie.hanse.issuetracker.oauth;

import lombok.RequiredArgsConstructor;
import louie.hanse.issuetracker.OAuthProperties;
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
            oAuthProperties.clientId, oAuthProperties.clientSecret, code);

        return WebClient.create()
            .post().uri(oAuthProperties.accessTokenApiUrl)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(accessTokenRequest)
            .retrieve()
            .bodyToMono(GithubAccessToken.class)
            .blockOptional()

//            TODO 커스텀 예외 추가하기
            .orElseThrow(IllegalStateException::new);
    }

    public GithubUser getUserInfo(GithubAccessToken githubAccessToken) {
        return WebClient.create()
            .get().uri(oAuthProperties.userApiUrl)
            .accept(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, githubAccessToken.getAuthorizationValue())
            .retrieve()
            .bodyToMono(GithubUser.class)
            .blockOptional()

//            TODO 커스텀 예외 추가하기
            .orElseThrow(IllegalStateException::new);
    }
}
