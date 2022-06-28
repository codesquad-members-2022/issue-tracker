package com.ron2ader.issuetracker.service;

import com.ron2ader.issuetracker.controller.issuedto.IssueDetail;
import com.ron2ader.issuetracker.controller.issuedto.IssueDetailResponse;
import com.ron2ader.issuetracker.controller.issuedto.IssueFilter;
import com.ron2ader.issuetracker.controller.issuedto.IssueSimpleResponse;
import com.ron2ader.issuetracker.controller.issuedto.IssuesResponse;
import com.ron2ader.issuetracker.controller.labeldto.LabelResponse;
import com.ron2ader.issuetracker.controller.memberdto.MemberDto;
import com.ron2ader.issuetracker.controller.milestonedto.MilestoneResponse;
import com.ron2ader.issuetracker.domain.issue.*;
import com.ron2ader.issuetracker.domain.label.LabelRepository;
import com.ron2ader.issuetracker.domain.member.Member;
import com.ron2ader.issuetracker.domain.member.MemberRepository;
import com.ron2ader.issuetracker.domain.milestone.Milestone;
import com.ron2ader.issuetracker.domain.milestone.MilestoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;
    private final MemberRepository memberRepository;
    private final LabelRepository labelRepository;
    private final MilestoneRepository milestoneRepository;
    private final IssueLabelRepository issueLabelRepository;
    private final IssueAssigneeRepository issueAssigneeRepository;

    @Transactional
    public Long registerIssue(String issuerId, String title, String contents,
        List<Long> assigneeIds, List<Long> labelIds, Long milestoneId) {

        Member member = memberRepository.findByMemberId(issuerId).orElseThrow(NoSuchElementException::new);
        Issue issue = Issue.createIssue(member, title, contents);

        try {

            labelRepository.findAllById(labelIds).stream()
                .map(label -> IssueLabel.of(issue, label))
                .forEach(issue::addLabel);

            memberRepository.findAllById(assigneeIds).stream()
                .map(assignee -> IssueAssignee.of(issue, assignee))
                .forEach(issue::addAssignee);

        } catch (IllegalArgumentException exception) {
            throw new NoSuchElementException(exception.getMessage());
        }

        if (milestoneId != null) {
            Milestone milestone = milestoneRepository.findById(milestoneId).orElseThrow(NoSuchElementException::new);
            issue.setMilestone(milestone);
        }

        Issue savedIssue = issueRepository.save(issue);

        return savedIssue.getId();
    }

    // 상세 정보
    @Transactional(readOnly = true)
    public IssueDetailResponse findById(Long issueNumber) {
        Issue issue = issueRepository.findById(issueNumber)
            .orElseThrow(() -> new NoSuchElementException("해당하는 이슈가 없습니다."));

        List<MemberDto> assignees = issueAssigneeRepository.findByIssue(issue)
            .stream()
            .map(IssueAssignee::getAssignee)
            .map(MemberDto::from)
            .collect(Collectors.toList());

        List<LabelResponse> labels = issueLabelRepository.findByIssue(issue)
            .stream()
            .map(IssueLabel::getLabel)
            .map(LabelResponse::from)
            .collect(Collectors.toList());

        return new IssueDetailResponse(
            MemberDto.from(issue.getIssuer()),
            IssueDetail.from(issue),
            assignees,
            labels,
            MilestoneResponse.from(issue.getMilestone())
        );
    }

    @Transactional(readOnly = true)
    public Page<IssueSimpleResponse> findByOpenStatus(Pageable pageable, Boolean openStatus) {
        Page<Issue> issues = issueRepository.findAllByOpenStatus(pageable, openStatus);
        return issues.map(IssueSimpleResponse::from);
    }

    @Transactional(readOnly = true)
    public Long countByStatus(Boolean openStatus) {
        return issueRepository.countByOpenStatus(openStatus);
    }

    @Transactional(readOnly = true)
    public IssuesResponse findByIssueFilter(IssueFilter issueFilter) {
        List<Issue> findIssues = issueRepository.findByIssueFilter(issueFilter);
        long allCount = findIssues.size();

        long openCount = 0, closeCount = 0;
        if (issueFilter.getOpenStatus()) {
            openCount = findIssues.stream()
                .map(Issue::getOpenStatus)
                .filter(bol -> bol == issueFilter.getOpenStatus())
                .count();

            closeCount = allCount - openCount;
        } else {
            closeCount = findIssues.stream()
                .map(Issue::getOpenStatus)
                .filter(bol -> bol == issueFilter.getOpenStatus())
                .count();

            openCount = allCount - closeCount;
        }

        return new IssuesResponse(openCount, closeCount,
            findIssues.stream()
                .map(issue -> IssueSimpleResponse.from(issue))
                .collect(Collectors.toList())
            );
    }
}
