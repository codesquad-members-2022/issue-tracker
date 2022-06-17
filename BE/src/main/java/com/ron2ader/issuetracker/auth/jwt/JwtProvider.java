package com.ron2ader.issuetracker.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;

    private static final long ACCESS_EXPIRATION_TIME = 15 * 60 * 1000;
    private static final long REFRESH_EXPIRATION_TIME = 60 * 60 * 1000 * 3;

    private final Key secretkey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final JwtParser jwtParser = Jwts.parserBuilder()
                .requireIssuer(jwtProperties.getIssuer())
                .setSigningKey(secretkey)
                .build();

    public String generateAccessToken(String userId) {
        return generateToken(userId, jwtProperties.getSubjectAccess(), ACCESS_EXPIRATION_TIME);
    }

    public String generateRefreshToken(String userId) {
        return generateToken(userId, jwtProperties.getSubjectRefresh(), REFRESH_EXPIRATION_TIME);
    }

    public Claims parseToken(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    private String generateToken(String userId, String subject, long expirationTime) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime);
        return Jwts.builder()
                .setSubject(subject)
                .setIssuer(jwtProperties.getIssuer())
                .setAudience(userId)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(secretkey)
                .compact();
    }

}
