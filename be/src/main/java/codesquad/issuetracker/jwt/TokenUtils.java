package codesquad.issuetracker.jwt;

import codesquad.issuetracker.exception.InvalidTokenException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

public class TokenUtils {

    public static final String ACCESS_TOKEN_TYPE = "Bearer ";
    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh-token";

    private TokenUtils() { }

    public static String getAccessToken(HttpServletRequest request) {
        String valueOfAuthorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (valueOfAuthorizationHeader == null || !valueOfAuthorizationHeader.startsWith(ACCESS_TOKEN_TYPE)) {
            throw new InvalidTokenException("access 토큰이 존재하지 않습니다.");
        }

        return valueOfAuthorizationHeader.substring(ACCESS_TOKEN_TYPE.length());
    }

    public static String getRefreshToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String refreshToken = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(REFRESH_TOKEN_COOKIE_NAME)) {
                refreshToken = cookie.getValue();
                break;
            }
        }

        if (refreshToken == null) {
            throw new InvalidTokenException("refresh 토큰이 존재하지 않습니다.");
        }

        return refreshToken;
    }
}
