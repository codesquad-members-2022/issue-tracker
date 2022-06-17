package louie.hanse.issuetracker.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@RestController
@Slf4j
public class LoginController {


    private final String CLIENT_ID = "2e27ae458bb2d6e9f6cb";
    private final String CALLBACK_URL = "http://localhost:8080/login/callback";
    private final String CLIENT_SECRET = "135a542b437cdb1cb39401958e209fca297a8695";
    private final String ACCESS_TOKEN_API_URL = "https://github.com/login/oauth/access_token";
    private final String ACCESS_SCOPE = "user";

    @GetMapping("/login")
    public ResponseEntity loginForm(){
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
                .location(URI.create("https://github.com/login/oauth/authorize?client_id="
                        + CLIENT_ID +"&scope=" + ACCESS_SCOPE + "&redirect_uri=" + CALLBACK_URL))
                .build();
    }

    @GetMapping("/login/callback")
    public void login(String code, String scope){
        GithubAccessToken githubAccessToken = WebClient.create()
                .post().uri(ACCESS_TOKEN_API_URL)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(new GithubAccessTokenRequest(CLIENT_ID, CLIENT_SECRET, code))
                .retrieve()
                .bodyToMono(GithubAccessToken.class)
                .blockOptional()
                .orElseThrow(IllegalStateException::new);
        log.info("githubAccessToken: {}", githubAccessToken);

        GithubUser userInfo = WebClient.create()
                .get().uri("https://api.github.com/user")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + githubAccessToken.getAccessToken())
                .retrieve()
                .bodyToMono(GithubUser.class)
                .blockOptional()
                .orElseThrow(IllegalStateException::new);

        log.info("authorization: {}", userInfo);
    }




}
