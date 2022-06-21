package be.codesquad.issuetracker.oauth;

import be.codesquad.issuetracker.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class JwtFactory {

    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final int EXPIRED_SECOND = 24 * 60 * 60;

    public static String create(User user) {
        Date now = new Date();
        Date expiredTime = new Date(now.getTime() + EXPIRED_SECOND);
        return Jwts.builder()
            .setHeader(createJwtHeader())
            .setClaims(createJwtClaims(user))
            .setExpiration(expiredTime)
            .signWith(KEY, SignatureAlgorithm.HS256)
            .compact();
    }

    private static Map<String, Object> createJwtHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        return header;
    }

    private static Map<String, Object> createJwtClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authId", user.getAuthId());
        claims.put("username", user.getUsername());
        claims.put("imageUrl", user.getImageUrl());
        return claims;
    }
}
