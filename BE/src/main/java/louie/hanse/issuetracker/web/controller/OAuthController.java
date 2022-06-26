package louie.hanse.issuetracker.web.controller;

import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import louie.hanse.issuetracker.login.jwt.JwtProvider;
import louie.hanse.issuetracker.login.oauth.GithubAccessToken;
import louie.hanse.issuetracker.login.oauth.GithubUser;
import louie.hanse.issuetracker.login.oauth.OAuthProperties;
import louie.hanse.issuetracker.service.OAuthService;
import louie.hanse.issuetracker.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OAuthController {

    private final MemberService memberService;
    private final OAuthService oAuthService;
    private final OAuthProperties oAuthProperties;
    private final JwtProvider jwtProvider;

    @GetMapping("/login")
    public ResponseEntity loginForm() {
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
            .location(URI.create(oAuthProperties.getLoginFormUrl()))
            .build();
    }

    @GetMapping("/login/callback")
    public void login(String code, HttpServletResponse response) {
        GithubAccessToken githubAccessToken = oAuthService.getAccessToken(code);
        GithubUser githubUser = oAuthService.getUserInfo(githubAccessToken);
        Long memberId = memberService.login(githubUser);

        String accessToken = jwtProvider.createAccessToken(memberId);
        String refreshToken = jwtProvider.createRefreshToken(memberId);

        memberService.updateRefreshToken(refreshToken, memberId);

        response.setHeader("Access-Token", accessToken);
        response.setHeader("Refresh-Token", refreshToken);

        log.info("accessToken {}", accessToken);

    }

    @GetMapping("/reissue/access-token")
    public void reissueAccessToken(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = request.getHeader("Access-Token");
        String refreshToken = request.getHeader("Refresh-Token");

        try {
            jwtProvider.verifyAccessToken(accessToken);
            response.setStatus(HttpStatus.FORBIDDEN.value());
        } catch (TokenExpiredException e) {
            jwtProvider.verifyRefreshToken(refreshToken);

            Long accessTokenMemberId = jwtProvider.decodeMemberId(accessToken);
            Long refreshTokenMemberId = jwtProvider.decodeMemberId(refreshToken);
            if (accessTokenMemberId != refreshTokenMemberId) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }

            String findRefreshToken = memberService.findRefreshTokenById(refreshTokenMemberId);
            if (!findRefreshToken.equals(refreshToken)){
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }

            String reissueAccessToken = jwtProvider.createAccessToken(refreshTokenMemberId);
            response.setHeader("Access-Token",  reissueAccessToken);

        }
    }

}
