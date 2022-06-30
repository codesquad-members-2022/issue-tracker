package team29.hoorry.issuetracker.core.member;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team29.hoorry.issuetracker.core.member.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByOauthId(Long oauthId);

	Optional<Member> findByLoginId(String loginId);
}
