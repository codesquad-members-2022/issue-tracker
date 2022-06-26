package be.codesquad.issuetracker.user.repository;

import be.codesquad.issuetracker.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByAuthId(String authId);
}
