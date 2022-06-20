package codesquad.issuetracker.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.security.Key;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public abstract class Token {

    @Getter
    protected String token;
    protected Key secretKey;

    @Getter
    @RequiredArgsConstructor
    enum ClaimKey {
        MEMBER_ID("memberId"),
        TOKEN_TYPE("tokenType");

        private final String keyName;
    }

    abstract public boolean validateExpirationOfToken();

    abstract public String getMemberId();

    abstract public long getRestOfExpiration();

    protected Claims getClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
