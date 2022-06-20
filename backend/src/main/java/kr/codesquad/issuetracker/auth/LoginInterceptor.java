package kr.codesquad.issuetracker.auth;

import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.codesquad.issuetracker.auth.exception.AuthHeaderNotFoundException;
import kr.codesquad.issuetracker.auth.exception.IllegalJwtFormatException;
import kr.codesquad.issuetracker.auth.jwt.JWTHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final JWTHandler jwtHandler;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        String userId = getUserId(request);

        return false;
    }

    private String getUserId(HttpServletRequest request)  {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        verifyHeader(authHeader);

        Claims claims = jwtHandler.decodeJwt(authHeader.split("Bearer ")[1]);
        return claims.get("userId", String.class);
    }


    private void verifyHeader(String authorization) {
        if (authorization.trim().isEmpty() || authorization == null) {
            throw new AuthHeaderNotFoundException();
        }
        if (!authorization.startsWith("Bearer")) {
            throw new IllegalJwtFormatException("Authorization 헤더는 'Bearer'로 시작해야 합니다.");
        }
    }

}
