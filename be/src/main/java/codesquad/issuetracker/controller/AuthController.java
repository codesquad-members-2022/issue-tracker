package codesquad.issuetracker.controller;

import codesquad.issuetracker.dto.ResponseMessage;
import codesquad.issuetracker.jwt.AccessToken;
import codesquad.issuetracker.jwt.AccessTokenProvider;
import codesquad.issuetracker.jwt.RefreshTokenProvider;
import codesquad.issuetracker.jwt.Token;
import codesquad.issuetracker.jwt.TokenManager;
import codesquad.issuetracker.jwt.TokenUtils;
import codesquad.issuetracker.service.OAuthService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final OAuthService oAuthService;
    private final AccessTokenProvider accessTokenProvider;
    private final RefreshTokenProvider refreshTokenProvider;
    private final TokenManager tokenManager;

    @GetMapping("/api/login")
    public ResponseMessage login(HttpServletResponse response, @RequestParam String code) {
        Long memberId = oAuthService.authorizeForThirdParty(code);
        Token accessToken = accessTokenProvider.createToken(String.valueOf(memberId));
        Token refreshToken = refreshTokenProvider.createToken(String.valueOf(memberId));

        tokenManager.saveRefreshTokenByMemberId(String.valueOf(memberId), refreshToken.getToken());

        response.addHeader("access-token", accessToken.getToken());
        response.addCookie(createCookieWithRefreshToken(refreshToken.getToken()));

        return new ResponseMessage(HttpStatus.OK, "로그인이 정상적으로 처리되었습니다.");
    }

    private Cookie createCookieWithRefreshToken(String refreshToken) {
        Cookie cookie = new Cookie("refresh-token", refreshToken);
        cookie.setPath("/api");
        cookie.setHttpOnly(true);
        return cookie;
    }

    @PostMapping("/api/access-token/reissue")
    public ResponseMessage reissue(HttpServletRequest request, HttpServletResponse response) {
        Token refreshToken = refreshTokenProvider.convertToObject(TokenUtils.getRefreshToken(request));
        if (!refreshToken.validateExpirationOfToken()) {
            throw new IllegalArgumentException("refresh 토큰의 만료기한이 지났습니다. 다시 로그인 해주세요.");
        }
        AccessToken renewedAccessToken = accessTokenProvider.createToken(refreshToken.getMemberId());
        response.addHeader("access-token", renewedAccessToken.getToken());

        return new ResponseMessage(HttpStatus.OK, "access 토큰이 갱신되었습니다.");
    }

    @GetMapping("/api/logout")
    public ResponseMessage logout(HttpServletRequest request) {
        Token accessToken = accessTokenProvider.convertToObject(TokenUtils.getAccessToken(request));
        Token refreshToken = refreshTokenProvider.convertToObject(TokenUtils.getRefreshToken(request));

        tokenManager.invalidateToken(accessToken, refreshToken);

        return new ResponseMessage(HttpStatus.OK, "로그아웃이 처리되었습니다.");
    }
}
