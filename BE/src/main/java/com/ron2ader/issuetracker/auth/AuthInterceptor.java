package com.ron2ader.issuetracker.auth;

import com.ron2ader.issuetracker.auth.jwt.AuthToken;
import com.ron2ader.issuetracker.auth.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (isPreFlight(request.getMethod())) {
            return true;
        }

        String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (tokenHeader == null) {
            return false;
        }

        AuthToken authToken = AuthToken.from(tokenHeader);

        Claims claims = jwtProvider.verifyToken(authToken.getToken());
        String audience = claims.getAudience();

        request.setAttribute("userId", audience);
        return true;
    }

    private boolean isPreFlight(String httpMethod) {
        if (httpMethod.matches(HttpMethod.OPTIONS.name())) {
            return true;
        }
        return false;
    }
}
