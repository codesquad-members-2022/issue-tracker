package kr.codesquad.issuetracker.auth;

import javax.servlet.http.HttpServletRequest;
import kr.codesquad.issuetracker.auth.jwt.JWT;
import kr.codesquad.issuetracker.auth.jwt.JWTHandler;
import kr.codesquad.issuetracker.common.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final JWTHandler jwtUtil;

    @GetMapping("/oauth/callback")
    public LoginResponse oauthLogin(@RequestParam String code) {
        AccessToken accessToken = loginService.getAccessToken(code);
        GitHubUserInfo gitHubUserInfo = loginService.getGitHubUserInfo(accessToken);
        loginService.saveUser(gitHubUserInfo);
        JWT jwt = jwtUtil.createToken(gitHubUserInfo);

        return new LoginResponse(jwt.getJwt(), ResponseMessage.LONGIN_SUCCESS);
    }

}
