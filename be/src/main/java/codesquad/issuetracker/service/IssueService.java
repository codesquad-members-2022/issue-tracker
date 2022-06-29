package codesquad.issuetracker.service;

import codesquad.issuetracker.domain.Assignee;
import codesquad.issuetracker.domain.Issue;
import codesquad.issuetracker.domain.IssueLabel;
import codesquad.issuetracker.domain.IssueStatus;
import codesquad.issuetracker.domain.Label;
import codesquad.issuetracker.domain.Member;
import codesquad.issuetracker.domain.Milestone;
import codesquad.issuetracker.domain.Reply;
import codesquad.issuetracker.dto.issue.form.IssueCreateForm;
import codesquad.issuetracker.dto.issue.IssueDto;
import codesquad.issuetracker.dto.issue.IssueDtos;
import codesquad.issuetracker.dto.issue.IssueSearchCondition;
import codesquad.issuetracker.dto.issue.form.IssueStatusUpdateForm;
import codesquad.issuetracker.dto.reply.ReplyDto;
import codesquad.issuetracker.dto.reply.ReplyForm;
import codesquad.issuetracker.repository.AssigneeRepository;
import codesquad.issuetracker.repository.IssueLabelRepository;
import codesquad.issuetracker.repository.IssueRepository;
import codesquad.issuetracker.repository.LabelRepository;
import codesquad.issuetracker.repository.MemberRepository;
import codesquad.issuetracker.repository.MilestoneRepository;
import codesquad.issuetracker.repository.ReplyRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IssueService {

    private final IssueRepository issueRepository;
    private final MemberRepository memberRepository;
    private final MilestoneRepository milestoneRepository;
    private final LabelRepository labelRepository;
    private final IssueLabelRepository issueLabelRepository;
    private final AssigneeRepository assigneeRepository;

    public IssueDtos getIssuesByCriteria(IssueSearchCondition condition) {
        Set<String> labelConditions = condition.parseLabelConditions();
        Set<String> exclusionConditions = condition.parseExclusionConditions();
        List<Issue> issues = issueRepository.search(condition, labelConditions,
            exclusionConditions);

        List<IssueDto> selectedIssues = issues
            .stream()
            .filter(issue -> issue.hasSameStatus(condition.getStatus()))
            .map(IssueDto::from)
            .collect(Collectors.toList());

        long totalCount = issues.size();
        long opened = countOpened(totalCount, selectedIssues.size(), condition.isOpenStatus());

        return new IssueDtos(opened, totalCount - opened, selectedIssues);
    }

    private long countOpened(long totalCount, long countOfSelectedIssues, boolean isOpenStatus) {
        if (isOpenStatus) {
            return countOfSelectedIssues;
        }

        return totalCount - countOfSelectedIssues;
    }

    public IssueDto getIssueDtoById(Long id) {
        Issue issue = getIssueById(id);

        return IssueDto.from(issue);
    }

    @Transactional
    public IssueDto createIssue(IssueCreateForm form) {
        Member writer = getMemberById(form.getWriterId());
        Milestone milestone = getMilestoneById(form.getMilestoneId());
        Issue issue = Issue.createIssue(writer, milestone, form.getSubject(), IssueStatus.OPEN);

        Reply reply = Reply.createReply(issue, writer, form.getComment());
        issue.addReply(reply);

        List<Long> assigneeIds = form.getAssigneeIds();
        for (Long assigneeId : assigneeIds) {
            issue.addAssignee(Assignee.createAssignee(issue, getMemberById(assigneeId)));
        }

        List<Long> labelIds = form.getLabelIds();
        for (Long labelId : labelIds) {
            issue.addIssueLabel(IssueLabel.createIssueLabel(issue, getLabelById(labelId)));
        }

        issueRepository.save(issue);

        return IssueDto.from(issue);
    }

    @Transactional
    public void updateStatusOfIssue(IssueStatusUpdateForm form) {
        List<Issue> issues = issueRepository.findAllById(form.getIdOfIssues());
        IssueStatus updatedStatus = form.getUpdatedStatus();
        issues.forEach(issue -> issue.updateStatus(updatedStatus));
    }

    @Transactional
    public void updateSubjectOfIssue(Long id, String subject) {
        Issue issue = getIssueById(id);

        issue.updateSubject(subject);
    }

    @Transactional
    public void updateMilestoneOfIssue(Long id, Long milestoneId) {
        Issue issue = getIssueById(id);

        if (milestoneId == null) {
            issue.updateMilestone(null);
            return;
        }

        Milestone milestone = getMilestoneById(milestoneId);

        issue.updateMilestone(milestone);
    }

    @Transactional
    public void updateLabelsOfIssue(Long id, List<Long> ids) {
        Issue issue = getIssueById(id);
        issueLabelRepository.deleteByIssueId(issue.getId());

        if (ids.size() == 0) {
            return;
        }

        labelRepository.findAllById(ids)
            .forEach(label -> issue.addIssueLabel(IssueLabel.createIssueLabel(issue, label)));
    }

    @Transactional
    public void updateAssigneeOfIssue(Long id, List<Long> ids) {
        Issue issue = getIssueById(id);
        assigneeRepository.deleteByIssueId(issue.getId());

        if (ids.size() == 0) {
            return;
        }

        memberRepository.findAllById(ids)
            .forEach(member -> issue.addAssignee(Assignee.createAssignee(issue, member)));
    }

    @Transactional
    public void delete(Long id) {
        issueRepository.deleteById(id);
    }

    private Issue getIssueById(Long id) {
        return issueRepository.findById(id).orElseThrow(() -> {
            throw new IllegalStateException("존재하지 않는 이슈입니다.");
        });
    }

    private Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        });
    }

    private Milestone getMilestoneById(Long id) {
        if (id == null) {
            return null;
        }

        return milestoneRepository.findById(id).orElseThrow(() -> {
            throw new IllegalStateException("존재하지 않는 마일스톤입니다.");
        });
    }

    private Label getLabelById(Long id) {
        return labelRepository.findById(id).orElseThrow(() -> {
            throw new IllegalStateException("존재하지 않는 라벨입니다.");
        });
    }
}
