package codesquad.issuetracker.repository;

import static codesquad.issuetracker.domain.QAssignee.assignee;
import static codesquad.issuetracker.domain.QIssue.issue;
import static codesquad.issuetracker.domain.QIssueLabel.issueLabel;
import static codesquad.issuetracker.domain.QMember.member;
import static codesquad.issuetracker.domain.QMilestone.milestone;
import static codesquad.issuetracker.domain.QReply.reply;
import static codesquad.issuetracker.domain.QLabel.label;
import static org.springframework.util.StringUtils.hasText;

import codesquad.issuetracker.domain.Issue;
import codesquad.issuetracker.domain.IssueStatus;
import codesquad.issuetracker.domain.QAssignee;
import codesquad.issuetracker.dto.issue.IssueSearchCondition;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IssueRepository {

    private static final String EXCLUSION_CONDITION_LABEL = "LABEL";
    private static final String EXCLUSION_CONDITION_MILESTONE = "MILESTONE";
    private static final String EXCLUSION_CONDITION_ASSIGNEE = "ASSIGNEE";

    private final JPAQueryFactory queryFactory;

    public List<Issue> search(IssueSearchCondition condition, Set<String> exclusionConditions) {
        return queryFactory.selectFrom(issue).distinct()
            .join(issue.writer, member).fetchJoin()
            .leftJoin(issue.milestone, milestone).fetchJoin()
            .leftJoin(issue.assignees, assignee)
            .leftJoin(member).on(assignee.member.id.eq(member.id))
            .leftJoin(issue.replies, reply)
            .leftJoin(issue.issueLabels, issueLabel).fetchJoin()
            .leftJoin(label).on(issueLabel.label.id.eq(label.id))
            .where(statusEq(condition.getStatus().name()),
                writerIdentityEq(condition.getWriter()),
                milestoneSubjectEq(condition.getMilestone()),
                assigneeIdentityEq(condition.getAssignee()),
                replierIdentityEq(condition.getReplier()),
                labelNameEq(condition.getLabel()),
                isUnLabeled(exclusionConditions.contains(EXCLUSION_CONDITION_LABEL)),
                isNoMilestone(exclusionConditions.contains(EXCLUSION_CONDITION_MILESTONE)),
                isAssignedToNobody(exclusionConditions.contains(EXCLUSION_CONDITION_ASSIGNEE))
            )
            .fetch();
    }

    public Map<IssueStatus, Long> findCountOfIssuesByStatus(IssueSearchCondition condition, Set<String> exclusionConditions) {
        List<Tuple> tuples = queryFactory
            .select(issue.status, issue.id.countDistinct())
            .from(issue)
            .join(issue.writer, member)
            .leftJoin(issue.milestone, milestone)
            .leftJoin(issue.assignees, assignee)
            .leftJoin(issue.replies, reply)
            .leftJoin(issue.issueLabels, issueLabel)
            .where(writerIdentityEq(condition.getWriter()),
                milestoneSubjectEq(condition.getMilestone()),
                assigneeIdentityEq(condition.getAssignee()),
                replierIdentityEq(condition.getReplier()),
                labelNameEq(condition.getLabel()),
                isUnLabeled(exclusionConditions.contains(EXCLUSION_CONDITION_LABEL)),
                isNoMilestone(exclusionConditions.contains(EXCLUSION_CONDITION_MILESTONE)),
                isAssignedToNobody(exclusionConditions.contains(EXCLUSION_CONDITION_ASSIGNEE))
            )
            .groupBy(issue.status)
            .fetch();

        Map<IssueStatus, Long> countOfIssuesByStatus = new HashMap<>();

        for (Tuple tuple : tuples) {
            countOfIssuesByStatus.put(tuple.get(issue.status), tuple.get(issue.id.countDistinct()));
        }

        return countOfIssuesByStatus;
    }

    public void update(IssueStatus updatedStatus, List<Long> idOfIssues) {
        queryFactory
            .update(issue)
            .set(issue.status, updatedStatus)
            .where(issue.id.in(idOfIssues))
            .execute();
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
