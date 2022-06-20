package codesquad.issuetracker.repository;

import static codesquad.issuetracker.domain.QAssignee.assignee;
import static codesquad.issuetracker.domain.QIssue.issue;
import static codesquad.issuetracker.domain.QIssueLabel.issueLabel;
import static codesquad.issuetracker.domain.QMember.member;
import static codesquad.issuetracker.domain.QMilestone.milestone;
import static codesquad.issuetracker.domain.QReply.reply;
import static org.springframework.util.StringUtils.hasText;

import codesquad.issuetracker.domain.IssueStatus;
import codesquad.issuetracker.domain.QAssignee;
import codesquad.issuetracker.dto.issue.IssueDto;
import codesquad.issuetracker.dto.issue.IssueSearchCondition;
import codesquad.issuetracker.dto.issue.QIssueDto;
import codesquad.issuetracker.dto.milestone.QMilestoneDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IssueRepository {

    private final JPAQueryFactory queryFactory;

    public List<IssueDto> search(IssueSearchCondition condition) {
        return queryFactory.select(
            new QIssueDto(
                issue.id,
                issue.subject,
                issue.description,
                member.identity,
                member.profileUrl,
                issue.createdDateTime,
                new QMilestoneDto(milestone.id, milestone.subject)
            )
        ).from(issue)
            .join(issue.writer, member)
            .leftJoin(issue.milestone, milestone)
            .leftJoin(issue.assignees, assignee)
            .leftJoin(issue.replies, reply)
            .leftJoin(issue.issueLabels, issueLabel)
            .where(statusEq(condition.getStatus().name()),
                writerIdentityEq(condition.getWriter()),
                milestoneSubjectEq(condition.getMilestone()),
                assigneeIdentityEq(condition.getAssignee()),
                replierIdentityEq(condition.getReplier()),
                labelNameEq(condition.getLabel())
            )
            .groupBy(issue.id)
            .fetch();
    }

    public Map<IssueStatus, Long> findCountOfIssuesByStatus() {
        List<Tuple> tuples = queryFactory
            .select(issue.status, issue.count())
            .from(issue)
            .groupBy(issue.status)
            .fetch();

        Map<IssueStatus, Long> countOfIssuesByStatus = new HashMap<>();

        for (Tuple tuple : tuples) {
            countOfIssuesByStatus.put(tuple.get(issue.status), tuple.get(issue.count()));
        }

        return countOfIssuesByStatus;
    }

    private BooleanExpression statusEq(String status) {
        return hasText(status) ? issue.status.eq(IssueStatus.valueOf(status)) : null;
    }

    private BooleanExpression writerIdentityEq(String writer) {
        return hasText(writer) ? issue.writer.identity.eq(writer) : null;
    }

    private BooleanExpression milestoneSubjectEq(String milestoneSubject) {
        return hasText(milestoneSubject) ? issue.milestone.subject.eq(milestoneSubject) : null;
    }

    private BooleanExpression assigneeIdentityEq(String assignee) {
        return hasText(assignee) ? QAssignee.assignee.member.identity.eq(assignee) : null;
    }

    private BooleanExpression replierIdentityEq(String replier) {
        return hasText(replier) ? reply.member.identity.eq(replier) : null;
    }

    private BooleanExpression labelNameEq(String labelName) {
        return hasText(labelName) ? issueLabel.label.name.eq(labelName) : null;
    }
}
