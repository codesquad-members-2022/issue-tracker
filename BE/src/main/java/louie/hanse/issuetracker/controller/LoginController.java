package louie.hanse.issuetracker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import louie.hanse.issuetracker.OAuthProperties;
import louie.hanse.issuetracker.oauth.GithubAccessToken;
import louie.hanse.issuetracker.oauth.GithubAccessTokenRequest;
import louie.hanse.issuetracker.oauth.GithubUser;
import louie.hanse.issuetracker.service.MemberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    private final OAuthProperties oAuthProperties;

    @GetMapping("/login")
    public ResponseEntity loginForm() {
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
            .location(URI.create(oAuthProperties.loginFormUrl))
            .build();
    }

    @GetMapping("/login/callback")
    public void login(String code) {
        GithubAccessToken githubAccessToken = WebClient.create()
            .post().uri(oAuthProperties.accessTokenApiUrl)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(new GithubAccessTokenRequest(oAuthProperties.clientId, oAuthProperties.clientSecret, code))
            .retrieve()
            .bodyToMono(GithubAccessToken.class)
            .blockOptional()
            .orElseThrow(IllegalStateException::new);
        log.info("githubAccessToken: {}", githubAccessToken);

        GithubUser githubUser = WebClient.create()
            .get().uri("https://api.github.com/user")
            .accept(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + githubAccessToken.getAccessToken())
            .retrieve()
            .bodyToMono(GithubUser.class)
            .blockOptional()
            .orElseThrow(IllegalStateException::new);
        log.info("authorization: {}", githubUser);

        memberService.login(githubUser);
    }

    @GetMapping("/test")
    public String test() {
        return oAuthProperties.toString();
    }
}
