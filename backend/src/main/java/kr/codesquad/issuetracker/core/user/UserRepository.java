package kr.codesquad.issuetracker.core.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserId(String userId);

    Optional<User> findByUserId(String userId);
}
