package codesquad.issuetracker.repository;

import static codesquad.issuetracker.domain.QMember.*;

import codesquad.issuetracker.dto.member.MemberDto;
import codesquad.issuetracker.dto.member.QMemberDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final JPAQueryFactory queryFactory;

    public List<MemberDto> findAll() {
        return queryFactory
            .select(new QMemberDto(
                member.id,
                member.identity,
                member.nickname,
                member.profileUrl
            ))
            .from(member)
            .fetch();
    }
}
