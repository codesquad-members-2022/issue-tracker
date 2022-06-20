package codesquad.backend.issuetracker.user.infrastructure;

import codesquad.backend.issuetracker.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserSecret(String userSecret);
}
