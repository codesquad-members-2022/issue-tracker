package codesquad.issuetracker.repository;

import static codesquad.issuetracker.domain.QAssignee.assignee;
import static codesquad.issuetracker.domain.QIssue.issue;
import static codesquad.issuetracker.domain.QIssueLabel.issueLabel;
import static codesquad.issuetracker.domain.QMember.member;
import static codesquad.issuetracker.domain.QMileStone.mileStone;
import static codesquad.issuetracker.domain.QReply.reply;
import static codesquad.issuetracker.domain.QLabel.label;
import static org.springframework.util.ObjectUtils.isEmpty;

import codesquad.issuetracker.domain.IssueStatus;
import codesquad.issuetracker.dto.issue.IssueDto;
import codesquad.issuetracker.dto.issue.IssueSearchCondition;
import codesquad.issuetracker.dto.issue.QIssueDto;
import codesquad.issuetracker.dto.label.LabelDto;
import codesquad.issuetracker.dto.label.QLabelDto;
import codesquad.issuetracker.dto.milestone.QMileStoneDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
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
                new QMileStoneDto(mileStone.id, mileStone.subject)
            )
        ).from(issue)
            .join(issue.member, member)
            .leftJoin(issue.mileStone, mileStone)
            .leftJoin(issue.assignees, assignee)
            .leftJoin(issue.replies, reply)
            .leftJoin(issue.issueLabels, issueLabel)
            .where(statusEq(condition.getStatus()),
                writerIdEq(condition.getWriterId()),
                mileStoneIdEq(condition.getMilestoneId()),
                assigneeIdEq(condition.getAssigneeId()),
                replierIdEq(condition.getReplierId()),
                labelIdEq(condition.getLabelId())
            )
            .groupBy(issue.id)
            .fetch();
    }

    public List<LabelDto> findLabelsOfIssue(Long issueId) {
        return queryFactory
            .select(new QLabelDto(label.id, label.name, label.description, label.color))
            .from(issue)
            .join(issue.issueLabels, issueLabel)
            .join(label).on(issueLabel.label.id.eq(label.id))
            .where(issue.id.eq(issueId))
            .fetch();
    }

    private BooleanExpression statusEq(String status) {
        return isEmpty(status) ? null : issue.status.eq(IssueStatus.valueOf(status));
    }

    private BooleanExpression writerIdEq(Long writerId) {
        return writerId == null ? null : issue.member.id.eq(writerId);
    }

    private BooleanExpression mileStoneIdEq(Long mileStoneId) {
        return mileStoneId == null ? null : issue.mileStone.id.eq(mileStoneId);
    }

    private BooleanExpression assigneeIdEq(Long assigneeId) {
        return assigneeId == null ? null : assignee.member.id.eq(assigneeId);
    }

    private BooleanExpression replierIdEq(Long replierId) {
        return replierId == null ? null : reply.member.id.eq(replierId);
    }

    private BooleanExpression labelIdEq(Long labelId) {
        return labelId == null ? null : issueLabel.label.id.eq(labelId);
    }

}
