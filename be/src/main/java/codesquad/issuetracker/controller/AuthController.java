package codesquad.issuetracker.controller;

import codesquad.issuetracker.dto.ResponseMessage;
import codesquad.issuetracker.jwt.AccessToken;
import codesquad.issuetracker.jwt.AccessTokenProvider;
import codesquad.issuetracker.jwt.RefreshTokenProvider;
import codesquad.issuetracker.jwt.Token;
import codesquad.issuetracker.service.RedisService;
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
    private final AccessTokenProvider accessTokenProvider;
    private final RefreshTokenProvider refreshTokenProvider;
    private final RedisService redisService;

    @GetMapping("/api/login")
    public ResponseMessage login(HttpServletResponse response, @RequestParam String code) {
        Long memberId = githubOAuthClient.authorizeForThirdParty(code);
        Token accessToken = accessTokenProvider.createToken(String.valueOf(memberId));
        Token refreshToken = refreshTokenProvider.createToken(String.valueOf(memberId));

        redisService.saveRefreshTokenByMemberId(String.valueOf(memberId), refreshToken.getToken());

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
        Token refreshToken = refreshTokenProvider.convertToObject(TokenUtils.getRefreshToken(request));
        redisService.validateDurationOfRefreshToken(refreshToken.getMemberId());
        AccessToken renewedAccessToken = accessTokenProvider.createToken(refreshToken.getMemberId());
        response.addHeader("access-token", renewedAccessToken.getToken());

        return new ResponseMessage(HttpStatus.OK, "access 토큰이 갱신되었습니다.");
    }

    @GetMapping("/api/logout")
    public ResponseMessage logout(HttpServletRequest request) {
        Token accessToken = accessTokenProvider.convertToObject(TokenUtils.getAccessToken(request));
        Token refreshToken = refreshTokenProvider.convertToObject(TokenUtils.getRefreshToken(request));

        redisService.invalidateToken(accessToken, refreshToken);

        return new ResponseMessage(HttpStatus.OK, "로그아웃이 처리되었습니다.");
    }
}
