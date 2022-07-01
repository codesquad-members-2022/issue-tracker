package team25.issuetracker.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team25.issuetracker.domain.OauthRefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<OauthRefreshToken,Long> {
	Optional<OauthRefreshToken> findByRefreshToken(String refreshToken);
	boolean existsByKeyEmail(String userEmail);
	void deleteByKeyEmail(String userEmail);
}
