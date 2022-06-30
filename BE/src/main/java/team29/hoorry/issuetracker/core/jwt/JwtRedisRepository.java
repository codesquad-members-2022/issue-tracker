package team29.hoorry.issuetracker.core.jwt;

import org.springframework.data.repository.CrudRepository;

public interface JwtRedisRepository extends CrudRepository<RefreshToken, String> {

}
