package com.ron2ader.issuetracker.auth;

import com.ron2ader.issuetracker.service.AuthService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (isPreflightRequest(request)) {
            return true;
        }

        String token = AuthorizationExtractor.extract(request);

        String userId = authService.extractUserIdByToken(token);

        request.setAttribute("userId", userId);

        return true;
    }

    private boolean isPreflightRequest(HttpServletRequest request) {
        return request.getMethod().equals(HttpMethod.OPTIONS.name());
    }
}
