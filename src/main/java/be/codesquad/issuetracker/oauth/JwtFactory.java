package be.codesquad.issuetracker.oauth;

import be.codesquad.issuetracker.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtFactory {

    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.ES256);

    public static String create(User user, int expiredSecond) {
        Date now = new Date();
        Date expiredTime = new Date(now.getTime() + expiredSecond);
        return Jwts.builder()
            .setHeader(createJwtHeader())
            .setClaims(createJwtClaims(user))
            .setExpiration(expiredTime)
            .signWith(KEY, SignatureAlgorithm.ES256)
            .compact();
    }

    private static Map<String, Object> createJwtHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());
        return header;
    }

    private static Map<String, Object> createJwtClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authId", user.getAuthId());
        return claims;
    }
}
