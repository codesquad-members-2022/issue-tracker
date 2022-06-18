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
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (isPreFlightRequest(request.getMethod())) {
            return true;
        }

        String token = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .orElseThrow(RuntimeException::new); // 예외정의 및 처리 필요

        AuthToken authToken = AuthToken.from(token);

        Claims claims = jwtProvider.parseToken(authToken.getToken());
        String audience = claims.getAudience();

        request.setAttribute(HttpHeaders.AUTHORIZATION, audience);
        return true;
    }

    private boolean isPreFlightRequest(String httpMethod) {
        return httpMethod.matches(HttpMethod.OPTIONS.name());
    }

}
