package com.team33.backend.common.login.configuration;

import com.team33.backend.common.login.token.jwt.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.team33.backend.common.login.token.jwt.JwtTokenProvider.FIFTEEN_MINUTES;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider provider;

    public LoginInterceptor() {
        this.provider = new JwtTokenProvider();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(AUTHORIZATION);
        if (token == null) {
            return true;
        }
        String parsedToken = provider.parseToken(token);
        Claims accessTokenClaim = provider.getClaims(parsedToken);

        String parsedrefreshToken = request.getHeader("refreshToken");
        Claims refreshTokenClaim = provider.getClaims(parsedrefreshToken);

        Date accessTokenExpiration = accessTokenClaim.getExpiration();
        Date refreshToeknExpiration = refreshTokenClaim.getExpiration();

        Date currentTime = new Date();

        if (currentTime.before(accessTokenExpiration)) {
            log.info("AccessToken의 기한이 남았다면 갱신 후 처음으로 반환");
            response.setStatus(MOVED_PERMANENTLY.value());
            renewRefreshToken(response, accessTokenClaim, refreshTokenClaim);
            return false;
        }

        // AccessToken의 기한은 만료되고 RefreshToken은 남아있는 경우
        if (currentTime.before(accessTokenExpiration) && currentTime.before(refreshToeknExpiration)) {
            refreshAccessToken(accessTokenClaim);
            response.setStatus(MOVED_PERMANENTLY.value());
            renewRefreshToken(response, accessTokenClaim, refreshTokenClaim);
            return false;
        }
        return currentTime.after(accessTokenExpiration) && refreshToeknExpiration.after(currentTime);
    }

    private void renewRefreshToken(HttpServletResponse response, Claims accessTokenClaim, Claims refreshTokenClaim) throws IOException {
        String refreshedAccessToken = provider.refreshAccessToken(accessTokenClaim);
        String refreshedRefreshToken = provider.refreshAccessToken(refreshTokenClaim);
        response.addCookie(new Cookie("refreshToken", refreshedRefreshToken));
        response.getWriter().write(refreshedAccessToken);
    }

    private void refreshAccessToken(Claims accessTokenClaim) {
        Date accessTokenExpiration = new Date();
        accessTokenClaim.put("issuedAt", accessTokenExpiration);
        accessTokenClaim.put("expiration", new Date(System.currentTimeMillis() + FIFTEEN_MINUTES));
    }
}
