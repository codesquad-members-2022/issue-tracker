package codesquad.issuetracker.jwt;

import codesquad.issuetracker.exception.InvalidTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RefreshToken extends Token {

    public RefreshToken(String token, Key secretKey) {
        super.token = token;
        super.secretKey = secretKey;
    }

    RefreshToken(String memberId, Key secretKey, SignatureAlgorithm signatureAlgorithm, Date expiration) {
        super.secretKey = secretKey;
        super.token = compactToken(memberId, signatureAlgorithm, expiration);
    }

    private String compactToken(String memberId, SignatureAlgorithm signatureAlgorithm, Date expiration) {
        return Jwts.builder()
            .setClaims(makeClaims(memberId))
            .signWith(secretKey, signatureAlgorithm)
            .setExpiration(expiration)
            .compact();
    }

    private Map<String, Object> makeClaims(String memberId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(ClaimKey.TOKEN_TYPE.getKeyName(), TokenType.REFRESH.name());
        claims.put(ClaimKey.MEMBER_ID.getKeyName(), memberId);
        return claims;
    }

    @Override
    public boolean validateExpirationOfToken() {
        try {
            return !getClaims(token)
                .getExpiration()
                .before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String getMemberId() {
        try {
            return getClaims(token)
                .get(ClaimKey.MEMBER_ID.getKeyName())
                .toString();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        } catch (JwtException e) {
            throw new InvalidTokenException("유효하지 않은 토큰입니다.");
        }
    }

    @Override
    public long getRestOfExpiration() {
        try {
            return getClaims(token)
                .getExpiration()
                .getTime() - (new Date().getTime());
        } catch (ExpiredJwtException e) {
            return 0;
        } catch (JwtException e) {
            throw new InvalidTokenException("유효하지 않은 토큰입니다.");
        }
    }
}
