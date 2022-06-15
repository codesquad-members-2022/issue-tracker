package codesquad.shine.issuetracker.auth;

import codesquad.shine.issuetracker.exception.ErrorCode;
import codesquad.shine.issuetracker.exception.unchecked.NotAvailableException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenFactory {

    private final long accessTokenValidityTime;
    private final String secretKey;

    public JwtTokenFactory(@Value("${jwt.token.validate-time}") long accessTokenValidityTime, @Value("${jwt.token.token-secret}") String secretKey) {
        this.accessTokenValidityTime = accessTokenValidityTime;
        this.secretKey = secretKey;
    }

    public String createAccessToken(String payload) {
        Claims claims = Jwts.claims().setSubject(payload);
        Date createdDate = new Date();
        Date expirationDate = new Date(createdDate.getTime() + accessTokenValidityTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String parsePayload(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        } catch (JwtException e) {
            throw new NotAvailableException(ErrorCode.INVALID_TOKEN);
        }
    }
}
