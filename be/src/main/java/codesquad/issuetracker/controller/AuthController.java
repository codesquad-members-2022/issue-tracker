package codesquad.issuetracker.controller;

import codesquad.issuetracker.dto.ResponseMessage;
import codesquad.issuetracker.jwt.Token;
import codesquad.issuetracker.service.TokenService;
import codesquad.issuetracker.jwt.TokenUtils;
import codesquad.issuetracker.service.GithubOAuthClient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final GithubOAuthClient githubOAuthClient;
    private final TokenService tokenService;

    @GetMapping("/api/login")
    public ResponseMessage login(HttpServletResponse response, @RequestParam String code) {
        String memberId = String.valueOf(githubOAuthClient.authorizeForThirdParty(code));
        Token accessToken = tokenService.createAccessToken(memberId);
        Token refreshToken = tokenService.createRefreshToken(memberId);

        response.addHeader("access-token", accessToken.getToken());
        response.setHeader("Set-Cookie", ResponseCookie
            .from("refresh-token", refreshToken.getToken())
            .path("/api")
            .build()
            .toString());

        return new ResponseMessage(HttpStatus.OK, "로그인이 정상적으로 처리되었습니다.");
    }

    @PostMapping("/api/access-token/reissue")
    public ResponseMessage reissue(HttpServletRequest request, HttpServletResponse response) {
        Token refreshToken = tokenService.covertToRefreshTokenObject(TokenUtils.getRefreshToken(request));
        tokenService.validateDurationOfRefreshToken(refreshToken.getMemberId());
        Token renewedAccessToken = tokenService.createAccessToken(refreshToken.getMemberId());
        response.addHeader("access-token", renewedAccessToken.getToken());

        return new ResponseMessage(HttpStatus.OK, "access 토큰이 갱신되었습니다.");
    }

    @GetMapping("/api/logout")
    public ResponseMessage logout(HttpServletRequest request) {
        Token accessToken = tokenService.covertToAccessTokenObject(TokenUtils.getAccessToken(request));
        Token refreshToken = tokenService.covertToRefreshTokenObject(TokenUtils.getRefreshToken(request));
        tokenService.invalidateToken(accessToken, refreshToken);

        return new ResponseMessage(HttpStatus.OK, "로그아웃이 처리되었습니다.");
    }
}
