package be.codesquad.issuetracker.oauth;

import be.codesquad.issuetracker.oauth.dto.GithubToken;
import be.codesquad.issuetracker.oauth.dto.GithubUser;
import java.net.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GithubOAuthClient {

    private static String accessTokenPath = "https://github.com/login/oauth/access_token";
    private static String resourcePath = "https://api.github.com/user";
    private static String clientId = "ff50ff7342e90de02060";
    private static String clientSecret = "deaf964bb0ece843b7703b980ca9a7019874fe38";

    public GithubToken getToken(String code) {
        return WebClient.create()
            .post()
            .uri(URI.create(accessTokenPath))
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(getBodyValue(code))
            .retrieve()
            .bodyToMono(GithubToken.class)
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

    private MultiValueMap<String, Object> getBodyValue(String code) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("code", code);
        return body;
    }
}
