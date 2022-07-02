package com.team33.backend.issue.service;

import com.team33.backend.common.exception.issue.IssueGroupNotFoundException;
import com.team33.backend.common.exception.member.MemberNotFoundException;
import com.team33.backend.issue.controller.dto.MemberResponse;
import com.team33.backend.issue.controller.dto.issue.IssueListRequest;
import com.team33.backend.issue.controller.dto.issue.IssueListResponse;
import com.team33.backend.issue.controller.dto.issue.IssueResponse;
import com.team33.backend.issue.controller.dto.label.LabelResponse;
import com.team33.backend.issue.domain.Issue;
import com.team33.backend.issue.domain.IssueStatus;
import com.team33.backend.issue.domain.filter.IssueFilter;
import com.team33.backend.issue.repository.IssueRepository;
import com.team33.backend.issue.repository.query.IssueFilterQueryRepository;
import com.team33.backend.issuegroup.repository.IssueGroupRepository;
import com.team33.backend.member.domain.Member;
import com.team33.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final MemberRepository memberRepository;
    private final IssueRepository issueRepository;
    private final IssueGroupRepository issueGroupRepository;
    private final IssueFilterQueryRepository issueFilterQueryRepository;
    private final IssueFilterService issueFilterService;

    @Transactional(readOnly = true)
    public IssueListResponse findAllIssueWithStatus(IssueListRequest request) {

        long issueGroupId = request.getIssueGroupId();

        List<Issue> issues = issueRepository.findAllByIssueGroupIdAndIssueStatus(
                issueGroupId,
                request.getStatus(),
                request.getPageable());

        return getIssueListResponse(issueGroupId, issues);
    }

    private IssueListResponse getIssueListResponse(long issueGroupId, List<Issue> issues) {
        return new IssueListResponse(
                getIssueResponses(issues),
                countIssueByStatus(issueGroupId, IssueStatus.OPEN),
                countIssueByStatus(issueGroupId, IssueStatus.CLOSED)
        );
    }

    private List<IssueResponse> getIssueResponses(List<Issue> issues) {
        return issues.stream()
                .map(issue -> new IssueResponse(issue, getLabelResponseList(issue), getMemberResponseList(issue)))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<MemberResponse> getMemberResponseList(Issue issue) {
        return issue.getAssignees()
                .stream()
                .map(assignee -> MemberResponse.from(assignee.getMember()))
                .collect(Collectors.toList());
    }

    private List<LabelResponse> getLabelResponseList(Issue issue) {
        return issue.getLabels()
                .stream()
                .map(label -> LabelResponse.from(label.getLabel()))
                .collect(Collectors.toList());
    }

    public int countIssueByStatus(long issueGroupId, IssueStatus status) {
        return issueRepository.countByIssueGroupIdAndIssueStatus(issueGroupId, status);
    }

    @Transactional(readOnly = true)
    public IssueListResponse findAllIssueWithStatusAndFilter(IssueListRequest issueListRequest, String filterQuery, String githubId) {
        Member member = memberRepository.findByGithubId(githubId)
                .orElseThrow(() -> new MemberNotFoundException());

        List<IssueFilter> filters = issueFilterService.findAllMatchedFilters(filterQuery);

        long issueGroupId = issueListRequest.getIssueGroupId();
        Pageable pageable = issueListRequest.getPageable();

        if (!validateIssueGroup(issueListRequest.getIssueGroupId())) {
            throw new IssueGroupNotFoundException();
        }

        List<Issue> filteredIssues = issueFilterQueryRepository.findAllFilteredIssues(filters, issueGroupId, member.getId(), pageable);

        return getIssueListResponse(issueGroupId, filteredIssues);
    }

    private boolean validateIssueGroup(long issueGroupId) {
        return issueGroupRepository.existsById(issueGroupId);
    }
}
