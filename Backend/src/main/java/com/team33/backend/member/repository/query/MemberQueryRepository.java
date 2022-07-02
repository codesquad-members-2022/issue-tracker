package com.team33.backend.member.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team33.backend.issue.domain.Assignee;
import com.team33.backend.member.controller.dto.MemberQueryResponse;
import com.team33.backend.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.team33.backend.issue.domain.QAssignee.assignee;
import static com.team33.backend.issue.domain.QIssue.issue;
import static com.team33.backend.member.domain.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    // 보류
    public List<Member> findGroupMembersById(Long groupId) {
        Set<Long> assignees = new HashSet<>(queryFactory.select(assignee.member.id)
                .from(assignee)
                .join(assignee.issue, issue)
                .on(issue.issueGroup.id.eq(groupId))
                .distinct()
                .fetch());
        return queryFactory.selectFrom(member)
                .where(member.id.in(assignees))
                .fetch();
    }
}
