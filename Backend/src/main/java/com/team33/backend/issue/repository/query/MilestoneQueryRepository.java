package com.team33.backend.issue.repository.query;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team33.backend.issue.controller.dto.milestone.MilestoneCount;
import com.team33.backend.issue.controller.dto.milestone.MilestoneResponse;
import com.team33.backend.issue.domain.QIssue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.team33.backend.issue.domain.QIssue.issue;
import static com.team33.backend.issue.domain.QMilestone.milestone;

@Repository
@RequiredArgsConstructor
public class MilestoneQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<MilestoneResponse> findMilestoneByIssuegroupId(Long milestoneId) {
        return queryFactory.select(
                        Projections.fields(MilestoneResponse.class,
                                issue.milestone.id, milestone.title, milestone.deadline))
                .from(issue)
                .where(issue.milestone.id.eq(milestoneId))
                .fetch();
    }
}
