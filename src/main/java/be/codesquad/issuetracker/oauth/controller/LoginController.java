package be.codesquad.issuetracker.oauth.controller;

import be.codesquad.issuetracker.oauth.service.AuthService;
import be.codesquad.issuetracker.oauth.service.JwtFactory;
import be.codesquad.issuetracker.oauth.service.LoginService;
import be.codesquad.issuetracker.oauth.dto.GithubUser;
import be.codesquad.issuetracker.oauth.dto.TokenInformation;
import be.codesquad.issuetracker.user.domain.User;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping("/oauth/github")
@RequiredArgsConstructor
public class LoginController {

    private static final String CLIENT_ID = "ff50ff7342e90de02060";
    private static final String REDIRECT_URI = "https://github.com/login/oauth/authorize";
    private static final int EXPIRED_SECOND = 24 * 60 * 60;

    private final LoginService loginService;
    private final AuthService githubOAuthClient;

    @GetMapping()
    public ResponseEntity<Void> githubLogin() {
        URI location = UriComponentsBuilder
            .fromPath(REDIRECT_URI)
            .queryParam("client_id", CLIENT_ID)
            .build()
            .toUri();

        return ResponseEntity.status(HttpStatus.FOUND)
            .header(HttpHeaders.LOCATION, location.toString())
            .build();
    }

    @GetMapping("/callback")
    public ResponseEntity<String> githubLoginCallback(
        @RequestParam(value = "code", required = false) String code
    ) {
        TokenInformation token = githubOAuthClient.getToken(code);
        GithubUser githubUser = githubOAuthClient.getUser(token.getAccessToken());
        User user = loginService.upsertUser(githubUser);
        String jwtToken = JwtFactory.create(user, EXPIRED_SECOND);
        log.debug("jwtToken: {}", jwtToken);

        // TODO: 2022/06/22 로그인 하기 직전의 페이지 url을 ?state= 로 받아뒀다가 body에 담아서 보내주는 방식
        return ResponseEntity.status(HttpStatus.OK)
            .body(jwtToken);
    }
}
