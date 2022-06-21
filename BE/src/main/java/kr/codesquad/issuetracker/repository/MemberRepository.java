package kr.codesquad.issuetracker.repository;

import kr.codesquad.issuetracker.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByGithubId(String githubId);
    boolean existsByGithubId(String githubId);
}
