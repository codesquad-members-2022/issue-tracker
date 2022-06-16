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

    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (tokenHeader == null) {
            return false;
        }

        String jwt = tokenHeader.replaceFirst("Bearer ", "");

        Claims claims = jwtProvider.verifyToken(jwt);
        String subject = claims.getSubject();
        String audience = claims.getAudience();

        if (subject.equals("refresh")) {
            response.setStatus(200);
            response.setHeader("access_token", jwtProvider.generateAccessToken(audience));
            response.setHeader("refresh_token", jwtProvider.generateRefreshToken(audience));
            return false;
        }

        request.setAttribute("userId", audience);
        return true;
    }
}
