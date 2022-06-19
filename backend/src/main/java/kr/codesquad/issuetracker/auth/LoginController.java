package kr.codesquad.issuetracker.auth;

import kr.codesquad.issuetracker.auth.jwt.JWT;
import kr.codesquad.issuetracker.auth.jwt.JWTUtil;
import kr.codesquad.issuetracker.common.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final JWTUtil jwtUtil;

    @GetMapping("/oauth/callback")
    public LoginResponse oauthLogin(@RequestParam String code) {
        AccessToken accessToken = loginService.getAccessToken(code);
        GitHubUserInfo gitHubUserInfo = loginService.getGitHubUserInfo(accessToken);
        JWT jwt = jwtUtil.createToken(gitHubUserInfo);

        return new LoginResponse(jwt.getJwt(), ResponseMessage.LONGIN_SUCCESS);
    }

}
