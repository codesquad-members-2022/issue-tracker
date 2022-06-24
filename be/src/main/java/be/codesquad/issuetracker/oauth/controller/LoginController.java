package be.codesquad.issuetracker.oauth.controller;

import be.codesquad.issuetracker.oauth.dto.GithubUser;
import be.codesquad.issuetracker.oauth.dto.TokenInformation;
import be.codesquad.issuetracker.oauth.service.AuthService;
import be.codesquad.issuetracker.oauth.service.JwtFactory;
import be.codesquad.issuetracker.oauth.service.LoginService;
import be.codesquad.issuetracker.user.domain.User;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequestMapping("/oauth/github")
@RestController
public class LoginController {

    private static final int EXPIRED_SECOND = 24 * 60 * 60;

    private final String clientId;
    private final String redirectUri;
    private final LoginService loginService;
    private final AuthService authService;

    public LoginController(
        @Value("${oauth.github.client-id}") String clientId,
        @Value("${oauth.github.redirect-uri}")String redirectUri,
        LoginService loginService,
        AuthService authService) {
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.loginService = loginService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<Void> githubLogin() {
        URI location = UriComponentsBuilder
            .fromPath(redirectUri)
            .queryParam("client_id", clientId)
            .build()
            .toUri();

        return ResponseEntity.status(HttpStatus.FOUND)
            .header(HttpHeaders.LOCATION, location.toString())
            .build();
    }

    @GetMapping("/callback")
    public ResponseEntity<String> githubLoginCallback(
        @RequestParam String code
    ) {
        TokenInformation token = authService.getToken(code);
        GithubUser githubUser = authService.getUser(token.getAccessToken());
        User user = loginService.saveUser(githubUser);
        String jwtToken = JwtFactory.create(user, EXPIRED_SECOND);
        log.debug("jwtToken: {}", jwtToken);

        // TODO: 2022/06/22 로그인 하기 직전의 페이지 url을 ?state= 로 받아뒀다가 body에 담아서 보내주는 방식
        return ResponseEntity.status(HttpStatus.OK)
            .body(jwtToken);
    }
}
