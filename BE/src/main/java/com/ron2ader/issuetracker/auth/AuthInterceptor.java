package com.ron2ader.issuetracker.auth;

import com.ron2ader.issuetracker.auth.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private static final String BEARER = "Bearer ";

    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (tokenHeader == null) {
            return false;
        }

        String[] headerAndValue = tokenHeader.split(" ");
        if (!isInvalidTokenType(headerAndValue[0])) {
            return false;
        }

        Claims claims = jwtProvider.verifyToken(headerAndValue[1]);
        String audience = claims.getAudience();

        request.setAttribute("userId", audience);
        return true;
    }

    private boolean isInvalidTokenType(String header) {
        if (header.equals(BEARER)) {
            return false;
        }
        return true;
    }
}
