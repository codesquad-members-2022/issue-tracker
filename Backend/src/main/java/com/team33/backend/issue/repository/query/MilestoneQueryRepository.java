package com.team33.backend.issue.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team33.backend.issue.controller.dto.milestone.MilestoneResponse;
import com.team33.backend.issue.domain.Milestone;
import com.team33.backend.issuegroup.domain.QIssueGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.team33.backend.issue.domain.QMilestone.milestone;

@Repository
@RequiredArgsConstructor
public class MilestoneQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Milestone> findMilestoneByIssuegroupId(Long issueGroupId) {
        QIssueGroup issueGroup = QIssueGroup.issueGroup;
        return queryFactory.selectFrom(milestone)
                .where(milestone.issueGroup.id.eq(issueGroupId))
                .fetch();
    }
}
