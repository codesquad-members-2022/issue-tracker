package codesquad.issuetracker.repository;

import static codesquad.issuetracker.domain.QMileStone.*;

import codesquad.issuetracker.dto.milestone.MileStoneDto;
import codesquad.issuetracker.dto.milestone.QMileStoneDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MileStoneRepository {

    private final JPAQueryFactory queryFactory;

    public List<MileStoneDto> findAll() {
        return queryFactory
            .select(new QMileStoneDto(
                mileStone.id,
                mileStone.subject
            ))
            .from(mileStone)
            .fetch();
    }
}
