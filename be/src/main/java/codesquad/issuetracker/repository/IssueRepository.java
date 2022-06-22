package codesquad.issuetracker.repository;

import static codesquad.issuetracker.domain.QAssignee.assignee;
import static codesquad.issuetracker.domain.QIssue.issue;
import static codesquad.issuetracker.domain.QIssueLabel.issueLabel;
import static codesquad.issuetracker.domain.QMember.member;
import static codesquad.issuetracker.domain.QMilestone.milestone;
import static codesquad.issuetracker.domain.QReply.reply;
import static org.springframework.util.StringUtils.hasText;

import codesquad.issuetracker.domain.Issue;
import codesquad.issuetracker.domain.IssueStatus;
import codesquad.issuetracker.domain.QAssignee;
import codesquad.issuetracker.dto.issue.IssueSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IssueRepository {

    private static final String EXCLUSION_CONDITION_LABEL = "label";
    private static final String EXCLUSION_CONDITION_MILESTONE = "milestone";
    private static final String EXCLUSION_CONDITION_ASSIGNEE = "assignee";

    private final JPAQueryFactory queryFactory;

    public List<Issue> search(IssueSearchCondition condition, Set<String> labelConditions,
        Set<String> exclusionConditions) {
        return queryFactory.selectFrom(issue).distinct()
            .join(issue.writer, member).fetchJoin()
            .leftJoin(issue.milestone, milestone).fetchJoin()
            .leftJoin(issue.assignees, assignee).fetchJoin()
            .leftJoin(issue.replies, reply)
            .leftJoin(issue.issueLabels, issueLabel)
            .where(
                writerIdentityEq(condition.getWriter()),
                milestoneSubjectEq(condition.getMilestone()),
                assigneeIdentityEq(condition.getAssignee()),
                replierIdentityEq(condition.getReplier()),
                labelNamesAllEq(labelConditions),
                isUnLabeled(exclusionConditions.contains(EXCLUSION_CONDITION_LABEL)),
                isNoMilestone(exclusionConditions.contains(EXCLUSION_CONDITION_MILESTONE)),
                isAssignedToNobody(exclusionConditions.contains(EXCLUSION_CONDITION_ASSIGNEE))
            )
            .fetch();
    }

    public void update(IssueStatus updatedStatus, List<Long> idOfIssues) {
        queryFactory
            .update(issue)
            .set(issue.status, updatedStatus)
            .where(issue.id.in(idOfIssues))
            .execute();
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

    private BooleanExpression labelNamesAllEq(Set<String> labelNames) {
        if (labelNames.size() < 1) {
            return null;
        }

        BooleanExpression booleanExpression = Expressions.asBoolean(false).isTrue();
        for (String labelName : labelNames) {
            booleanExpression = booleanExpression.or(labelNameEq(labelName));
        }
        return booleanExpression;
    }

    private BooleanExpression isUnLabeled(boolean flag) {
        return flag ? issueLabel.id.isNull() : null;
    }

    private BooleanExpression isNoMilestone(boolean flag) {
        return flag ? issue.milestone.id.isNull() : null;
    }

    private BooleanExpression isAssignedToNobody(boolean flag) {
        return flag ? assignee.id.isNull() : null;
    }
}
