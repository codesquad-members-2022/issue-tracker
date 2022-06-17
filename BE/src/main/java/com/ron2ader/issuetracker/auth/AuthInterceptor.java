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

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (Optional.ofNullable(token).isEmpty()) {
            return false;
        }

        AuthToken authToken = AuthToken.from(token);

        Claims claims = jwtProvider.parseToken(authToken.getToken());
        String audience = claims.getAudience();

        request.setAttribute("userId", audience);
        return true;
    }

    private boolean isPreFlightRequest(String httpMethod) {
        if (httpMethod.matches(HttpMethod.OPTIONS.name())) {
            return true;
        }
        return false;
    }

}
