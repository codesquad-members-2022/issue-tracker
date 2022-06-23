package codesquad.issuetracker.service;

import codesquad.issuetracker.exception.InvalidTokenException;
import codesquad.issuetracker.jwt.AccessTokenProvider;
import codesquad.issuetracker.jwt.RedisRepository;
import codesquad.issuetracker.jwt.RefreshTokenProvider;
import codesquad.issuetracker.jwt.Token;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private static final String LOGOUT_FLAG = "LOGOUT";
    private static final long REFRESH_TOKEN_DURATION_MINUTE = 2l;

    private final RedisRepository redisRepository;
    private final AccessTokenProvider accessTokenProvider;
    private final RefreshTokenProvider refreshTokenProvider;

    public Token createAccessToken(String memberId) {
        return accessTokenProvider.createToken(memberId);
    }

    public Token createRefreshToken(String memberId) {
        Token refreshToken = refreshTokenProvider.createToken(String.valueOf(memberId));
        redisRepository.save(memberId, refreshToken.getToken(), Duration.ofMinutes(REFRESH_TOKEN_DURATION_MINUTE));
        return refreshToken;
    }

    public Token covertToAccessTokenObject(String accessToken) {
        return accessTokenProvider.convertToObject(accessToken);
    }

    public Token covertToRefreshTokenObject(String refreshToken) {
        return refreshTokenProvider.convertToObject(refreshToken);
    }

    public void validateDurationOfRefreshToken(String memberId) {
        if (redisRepository.findByKey(memberId) == null) {
            throw new InvalidTokenException("refresh 토큰의 만료기한이 지났습니다. 다시 로그인 해주세요.");
        }
    }

    public boolean validateLogInStatusOfAccessToken(String accessToken) {
        return redisRepository.findByKey(accessToken) != LOGOUT_FLAG;
    }

    public void invalidateToken(Token accessToken, Token refreshToken) {
        redisRepository.save(accessToken.getToken(), LOGOUT_FLAG,
            Duration.ofMillis(accessToken.getRestOfExpiration()));
        redisRepository.deleteByKey(refreshToken.getMemberId());
    }
}
