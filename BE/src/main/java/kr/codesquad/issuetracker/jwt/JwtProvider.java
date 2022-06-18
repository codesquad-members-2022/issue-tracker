package kr.codesquad.issuetracker.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import kr.codesquad.issuetracker.domain.Member;
import kr.codesquad.issuetracker.dto.LoginResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

import static kr.codesquad.issuetracker.jwt.OAuthUtils.*;

@Component
public class JwtProvider {
    @Value("${ISSUER}")
    private String ISSUER;

    @Value("${SECRET-KEY}")
    private String SECRET_KEY;

    public LoginResponse createLoginResponse(Member member){
        Jwt jwt = createToken(member);
        return new LoginResponse(jwt.getJwt(), member.getImageUrl());
    }

    public Jwt createToken(Member member){
        String token = JWT.create()
                .withExpiresAt(new Date())
                .withClaim(GITHUB_NAME, member.getName())
                .withClaim(GITHUB_EMAIL, member.getEmail())
                .withClaim(GITHUB_ID, member.getGithubId())
                .withClaim(GITHUB_AVATAR_URL, member.getImageUrl())
                .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(SECRET_KEY));
        return new Jwt(token);
    }

    public DecodedJWT decodedToken(Jwt jwt){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .acceptExpiresAt(200000)
                .build();
        return verifier.verify(jwt.getJwt());
    }
}
