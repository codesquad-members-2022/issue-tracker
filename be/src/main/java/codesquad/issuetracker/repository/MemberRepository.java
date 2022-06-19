package codesquad.issuetracker.repository;

import static codesquad.issuetracker.domain.QMember.*;

import codesquad.issuetracker.domain.Member;
import codesquad.issuetracker.domain.MemberType;
import codesquad.issuetracker.dto.auth.AuthMemberInformation;
import codesquad.issuetracker.dto.member.MemberDto;
import codesquad.issuetracker.dto.member.QMemberDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public List<MemberDto> findAll() {
        return queryFactory
            .select(new QMemberDto(
                member.id,
                member.identity,
                member.name,
                member.profileUrl
            ))
            .from(member)
            .fetch();
    }

    public Member findByGithubIdentity(String identity) {
        return queryFactory
            .selectFrom(member)
            .where(
                member.type.eq(MemberType.GITHUB),
                member.identity.eq(identity))
            .fetchOne();
    }

    public Long save(AuthMemberInformation m) {
        Member member = Member.builder()
            .type(MemberType.GITHUB)
            .identity(m.getIdentity())
            .email(m.getEmail())
            .name(m.getName())
            .profileUrl(m.getProfileUrl())
            .build();

        em.persist(member);
        em.flush();

        return member.getId();
    }
}
