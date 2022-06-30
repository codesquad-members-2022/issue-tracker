package louie.hanse.issuetracker.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import louie.hanse.issuetracker.domain.*;
import louie.hanse.issuetracker.web.dto.IssueSearchRequest;

import java.util.List;

import static louie.hanse.issuetracker.domain.QComment.*;
import static louie.hanse.issuetracker.domain.QIssue.issue;
import static louie.hanse.issuetracker.domain.QIssueLabel.issueLabel;
import static louie.hanse.issuetracker.domain.QIssueManager.*;
import static louie.hanse.issuetracker.domain.QLabel.label;

@RequiredArgsConstructor
public class CustomIssueRepositoryImpl implements CustomIssueRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Issue> search(IssueSearchRequest request, Long userId) {
        return jpaQueryFactory.selectFrom(issue)
                .distinct()
                .leftJoin(issue.issueManagers, issueManager)
                .leftJoin(issue.comments, comment)
                .leftJoin(issue.issueLabels, issueLabel)
                .leftJoin(issueLabel.label, label)
                .where(
                        issue.status.eq(request.getStatus()),
                        issueWriterIdEq(request.getWriterId()),
                        issueManagerIdEq(request.getManagerId()),
                        commentWriterIdEq(userId),
                        labelIdEq(request.getLabelId()),
                        issueMilestoneIdEq(request.getMilestoneId())
                ).fetch();
    }

    @Override
    public long searchReverseStatusCount(IssueSearchRequest request, Long userId) {
        return jpaQueryFactory.select(issue.count())
                .from(issue)
                .leftJoin(issue.issueManagers, issueManager)
                .leftJoin(issue.comments, comment)
                .leftJoin(issue.issueLabels, issueLabel)
                .leftJoin(issueLabel.label, label)
                .where(
                        issue.status.eq(request.getStatus().reverse()),
                        issueWriterIdEq(request.getWriterId()),
                        issueManagerIdEq(request.getManagerId()),
                        commentWriterIdEq(userId),
                        labelIdEq(request.getLabelId()),
                        issueMilestoneIdEq(request.getMilestoneId())
                ).fetchOne();
    }

    private Predicate issueMilestoneIdEq(Long id) {
        return id == null ? null : issue.milestone.id.eq(id);
    }

    private Predicate labelIdEq(Long id) {
        return id == null ? null : label.id.eq(id);
    }

    private Predicate commentWriterIdEq(Long id) {
        return id == null ? null : comment.writer.id.eq(id);
    }

    private Predicate issueManagerIdEq(Long id) {
        return id == null ? null : issueManager.manager.id.eq(id);
    }

    private Predicate issueWriterIdEq(Long id) {
        return id == null ? null : issue.writer.id.eq(id);
    }
}
