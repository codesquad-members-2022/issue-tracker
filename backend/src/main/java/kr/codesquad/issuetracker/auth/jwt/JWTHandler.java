package kr.codesquad.issuetracker.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import kr.codesquad.issuetracker.auth.GitHubUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JWTHandler {

    private final JwtProperties jwtProperties;

    private JWTHandler() throws InstantiationException {
        throw new InstantiationException();
    }

    public JWT createToken(GitHubUserInfo gitHubUserInfo) {
        String jwt = Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setIssuer(jwtProperties.getIssuer())
            .claim("userId", gitHubUserInfo.getUserId())
            .claim("userName", gitHubUserInfo.getName())
            .claim("userEmail", gitHubUserInfo.getEmail())
            .signWith(createSecretKey())
            .compact();
        return new JWT(jwt);
    }

    public SecretKey createSecretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    }

}
