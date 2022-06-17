package be.codesquad.issuetracker.oauth;

import be.codesquad.issuetracker.oauth.dto.GithubToken;
import be.codesquad.issuetracker.oauth.dto.GithubUser;
import be.codesquad.issuetracker.user.User;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
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

    private static final int EXPIRED_SECOND = 24 * 60 * 60;

    private static final String CLIENT_ID = "ff50ff7342e90de02060";
    private static final String AUTHORIZE_PATH = "https://github.com/login/oauth/authorize";

    private final LoginService loginService;
    private final GithubOAuthClient githubOAuthClient;

    @GetMapping()
    public ResponseEntity<Void> githubLogin() {
        URI location = UriComponentsBuilder
            .fromPath(AUTHORIZE_PATH)
            .queryParam("client_id", CLIENT_ID)
            .build()
            .toUri();

        log.debug("location: {}", location);
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
            .header(HttpHeaders.LOCATION, location.toString())
            .build();
    }

    @GetMapping("/callback")
    public ResponseEntity<Void> githubLoginCallback(
        @RequestParam(value = "code", required = false) String code
    ) {
        GithubToken token = githubOAuthClient.getToken(code);
        GithubUser githubUser = githubOAuthClient.getUser(token.getAccessToken());
        User user = loginService.upsertUser(
            new User(
                githubUser.getGithubId(),
                githubUser.getUsername(),
                githubUser.getImageUrl()
            )
        );

        String accessToken = JwtFactory.create(user, EXPIRED_SECOND);
        ResponseCookie cookie = ResponseCookie.from("access_token", accessToken)
            .maxAge(EXPIRED_SECOND)
            .path("/")
            .build();

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .header(HttpHeaders.LOCATION, "/")
            .build();
    }
}
