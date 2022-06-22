package kr.codesquad.issuetraker.domain.issue;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.codesquad.issuetraker.dto.SearchFilterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class IssueRepositoryImpl implements IssueRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Issue> searchIssuesByFilter(SearchFilterDto searchFilterDto) {
        QIssue

        return queryFactory.selectFrom(issue)
    }
}
