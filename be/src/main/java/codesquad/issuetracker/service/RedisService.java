package codesquad.issuetracker.service;

import codesquad.issuetracker.exception.InvalidTokenException;
import codesquad.issuetracker.jwt.RedisRepository;
import codesquad.issuetracker.jwt.Token;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

    private static final String LOGOUT_FLAG = "LOGOUT";
    private static final long REFRESH_TOKEN_DURATION_MINUTE = 2l;

    private final RedisRepository redisRepository;

    public void saveRefreshTokenByMemberId(String memberId, String refreshToken) {
        redisRepository.save(memberId, refreshToken, Duration.ofMinutes(REFRESH_TOKEN_DURATION_MINUTE));
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
