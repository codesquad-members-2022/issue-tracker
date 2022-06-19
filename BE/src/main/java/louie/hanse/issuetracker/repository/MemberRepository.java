package louie.hanse.issuetracker.repository;

import louie.hanse.issuetracker.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsBySocialId(String socialId);
}
