package com.ron2ader.issuetracker.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    private static final long ACCESS_EXPIRATION_TIME = 15 * 60 * 1000;
    private static final long REFRESH_EXPIRATION_TIME = 60 * 60 * 1000 * 3;
    private static final String SUBJECT_ACCESS = "access";
    private static final String SUBJECT_REFRESH = "refresh";
    private static final String JWT_ISSUER = "aderon2";

    private final Key secretkey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final JwtParser jwtParser = Jwts.parserBuilder()
                .requireIssuer(JWT_ISSUER)
                .setSigningKey(secretkey)
                .build();

    public String generateAccessToken(String userId) {
        return generateToken(userId, SUBJECT_ACCESS, ACCESS_EXPIRATION_TIME);
    }

    public String generateRefreshToken(String userId) {
        return generateToken(userId, SUBJECT_REFRESH, REFRESH_EXPIRATION_TIME);
    }

    public Claims parseToken(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    private String generateToken(String userId, String subject, long expirationTime) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime);
        return Jwts.builder()
                .setSubject(subject)
                .setIssuer(JWT_ISSUER)
                .setAudience(userId)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(secretkey)
                .compact();
    }

}
