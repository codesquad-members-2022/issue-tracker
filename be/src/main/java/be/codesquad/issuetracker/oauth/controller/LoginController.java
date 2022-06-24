package be.codesquad.issuetracker.oauth.controller;

import be.codesquad.issuetracker.oauth.service.AuthService;
import be.codesquad.issuetracker.oauth.service.LoginService;
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

    private final String clientId;
    private final String redirectUri;
    private final LoginService loginService;

    public LoginController(
        @Value("${oauth.github.client-id}") String clientId,
        @Value("${oauth.github.redirect-uri}")String redirectUri,
        LoginService loginService,
        AuthService authService) {
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.loginService = loginService;
    }

    @GetMapping
    public ResponseEntity<Void> login() {
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
    public ResponseEntity<String> callback(@RequestParam String code) {
        String jwtToken = loginService.getJwtToken(code);
        log.debug("jwtToken: {}", jwtToken);
        // TODO: 2022/06/22 로그인 하기 직전의 페이지 url을 ?state= 로 받아뒀다가 body에 담아서 보내주는 방식
        return ResponseEntity.status(HttpStatus.OK)
            .body(jwtToken);
    }
}
