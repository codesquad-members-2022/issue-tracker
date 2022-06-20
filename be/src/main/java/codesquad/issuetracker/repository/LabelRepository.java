package codesquad.issuetracker.repository;

import static codesquad.issuetracker.domain.QIssueLabel.issueLabel;
import static codesquad.issuetracker.domain.QLabel.label;

import codesquad.issuetracker.dto.label.QLabelDto;
import codesquad.issuetracker.dto.label.LabelDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LabelRepository {

    private final JPAQueryFactory queryFactory;

    public List<LabelDto> findAll() {
        return queryFactory
            .select(new QLabelDto(
                label.id,
                label.name,
                label.description,
                label.color
            ))
            .from(label)
            .fetch();
    }

    public List<LabelDto> findAllByIssueId(Long issueId) {
        return queryFactory
            .select(new QLabelDto(label.id, label.name, label.description, label.color))
            .from(issueLabel)
            .join(label).on(issueLabel.label.id.eq(label.id))
            .where(issueLabel.issue.id.eq(issueId))
            .fetch();
    }
}
