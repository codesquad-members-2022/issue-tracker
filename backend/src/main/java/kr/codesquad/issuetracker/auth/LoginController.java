package kr.codesquad.issuetracker.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/oauth/callback")
    public void callBack(@RequestParam String code) {
        AccessToken accessToken = loginService.getAccessToken(code);
    }

}
