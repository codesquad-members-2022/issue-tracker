package louie.hanse.issuetracker.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import louie.hanse.issuetracker.OAuthProperties;
import louie.hanse.issuetracker.oauth.GithubAccessToken;
import louie.hanse.issuetracker.oauth.GithubUser;
import louie.hanse.issuetracker.oauth.OAuthService;
import louie.hanse.issuetracker.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    private final OAuthService oAuthService;
    private final OAuthProperties oAuthProperties;

    @GetMapping("/login")
    public ResponseEntity loginForm() {
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
            .location(URI.create(oAuthProperties.loginFormUrl))
            .build();
    }

    @GetMapping("/login/callback")
    public void login(String code) {
        GithubAccessToken githubAccessToken = oAuthService.getAccessToken(code);
        GithubUser githubUser = oAuthService.getUserInfo(githubAccessToken);
        memberService.login(githubUser);
    }

}
