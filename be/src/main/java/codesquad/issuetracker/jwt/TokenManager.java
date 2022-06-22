package codesquad.issuetracker.jwt;

import codesquad.issuetracker.exception.InvalidTokenException;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenManager {

    public static final String LOGOUT_FLAG = "LOGOUT";

    private final RedisTemplate<String, String> redisTemplate;

    public void saveRefreshTokenByMemberId(String memberId, String refreshToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(memberId, refreshToken, Duration.ofMinutes(2));
    }

    public void validateDurationOfRefreshToken(String memberId) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        if (valueOperations.get(memberId) == null) {
            throw new InvalidTokenException("refresh 토큰의 만료기한이 지났습니다. 다시 로그인 해주세요.");
        }
    }

    public boolean validateLogInStatusOfAccessToken(String accessToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(accessToken) != LOGOUT_FLAG;
    }

    public void invalidateToken(Token accessToken, Token refreshToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(accessToken.getToken(), LOGOUT_FLAG, Duration.ofMillis(
            accessToken.getRestOfExpiration()));

        redisTemplate.delete(refreshToken.getMemberId());
    }
}
