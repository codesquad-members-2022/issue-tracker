package kr.codesquad.issuetracker.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import kr.codesquad.issuetracker.domain.Member;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static kr.codesquad.issuetracker.jwt.OAuthUtils.*;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);
    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return verifyJwtToken(request);
    }

    private boolean verifyJwtToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        log.info("header : {}", header);
        if (header == null || !header.startsWith("Bearer")) {
            return false;
        }
        Jwt jwt = new Jwt(header.substring("Bearer".length()).trim());
        log.info("[Jwt] : {}", jwt);
        DecodedJWT decodedJWT = jwtProvider.decodedToken(jwt);

        Member members = Member.builder()
                .name(decodedJWT.getClaim(GITHUB_NAME).asString())
                .email(decodedJWT.getClaim(GITHUB_EMAIL).asString())
                .githubId(decodedJWT.getClaim(GITHUB_ID).asString())
                .imageUrl(decodedJWT.getClaim(GITHUB_AVATAR_URL).asString())
                .build();

        request.setAttribute("Members", members);
        return true;
    }
}
