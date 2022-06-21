package codesquad.issuetracker.repository;

import static codesquad.issuetracker.domain.QMilestone.*;

import codesquad.issuetracker.dto.milestone.MilestoneDto;
import codesquad.issuetracker.dto.milestone.QMilestoneDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MilestoneRepository {

    private final JPAQueryFactory queryFactory;

    public List<MilestoneDto> findAll() {
        return queryFactory
            .select(new QMilestoneDto(
                milestone.id,
                milestone.subject,
                milestone.description
            ))
            .from(milestone)
            .fetch();
    }
}
