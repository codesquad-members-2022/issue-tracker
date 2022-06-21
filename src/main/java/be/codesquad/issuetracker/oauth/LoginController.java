package be.codesquad.issuetracker.oauth;

import be.codesquad.issuetracker.oauth.dto.GithubUser;
import be.codesquad.issuetracker.oauth.dto.TokenInformation;
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

    private static final String CLIENT_ID = "ff50ff7342e90de02060";
    private static final String REDIRECT_URI = "https://github.com/login/oauth/authorize";

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
    public ResponseEntity<Void> githubLoginCallback(
        @RequestParam(value = "code", required = false) String code
    ) {
        TokenInformation token = githubOAuthClient.getToken(code);
        GithubUser githubUser = githubOAuthClient.getUser(token.getAccess_token());
        User user = loginService.upsertUser(githubUser);
        String jwtToken = JwtFactory.create(user);
        log.debug("jwtToken: {}", jwtToken);
//
//        ResponseCookie cookie = ResponseCookie.from("access_token", accessToken)
//            .maxAge(EXPIRED_SECOND)
//            .path("/")
//            .build();

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
//            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .header(HttpHeaders.LOCATION, "/")
            .build();
    }
}
