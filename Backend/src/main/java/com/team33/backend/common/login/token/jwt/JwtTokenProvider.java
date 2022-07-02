package com.team33.backend.common.login.token.jwt;

import com.team33.backend.common.login.token.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;

public class JwtTokenProvider implements TokenProvider {

    private static final String BEARER = "Bearer";
    private static final String TOKEN_DELIMETER = " ";
    private static final int FIRST_ELEMENT = 0;
    private static final int SECOND_ELEMENT = 1;
    public static final Long FIFTEEN_MINUTES = (long) (1000 * 60 * 30);
    public static final Long TWO_WEEKS = (long) (1000 * 60 * 12 * 24 * 7);
    private String secretKey;
    private Key key;

    public JwtTokenProvider() {
        this.secretKey = "a3fafoasdfiaj3o214sadfAasdfaofjoadsfjaosdf";
        this.key = getSigninKey();
    }

    private Key getSigninKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String githubId, long expire) {
        Claims claims = Jwts.claims();
        claims.put("githubId", githubId);
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String refreshAccessToken(Claims claims) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String parseToken(String token) {
        validatePrefix(token);
        return parseBearer(token);
    }

    private void validatePrefix(String token) {
        if (!startWithBearer(token)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean startWithBearer(String token) {
        return token.split(TOKEN_DELIMETER)[FIRST_ELEMENT].equals(BEARER);
    }

    public String parseBearer(String token) {
        return token.split(TOKEN_DELIMETER)[SECOND_ELEMENT];
    }

    public String findRefreshToken(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(name -> name.equals("refreshToken"))
                .map(Cookie::getValue)
                .findAny()
                .orElseThrow();
    }
}

