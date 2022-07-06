package louie.hanse.issuetracker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import louie.hanse.issuetracker.domain.*;
import louie.hanse.issuetracker.repository.*;
import louie.hanse.issuetracker.web.dto.issue.IssueDetailResponse;
import louie.hanse.issuetracker.web.dto.issue.IssueSaveRequest;
import louie.hanse.issuetracker.web.dto.issue.IssueSearchRequest;
import louie.hanse.issuetracker.web.dto.issue.IssueSearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class IssueService {
    private final IssueRepository issueRepository;
    private final MemberRepository memberRepository;
    private final LabelRepository labelRepository;
    private final MilestoneRepository milestoneRepository;

    @Transactional
    public void register(IssueSaveRequest issueSaveRequest, Long memberId) {
        Member writer = memberRepository.findById(memberId)
            .orElseThrow(IllegalStateException::new);
        Issue issue = new Issue(issueSaveRequest.getTitle(), writer);
        if (issueSaveRequest.getContents() != null) {
            Comment comment = new Comment(issue, issueSaveRequest.getContents());
        }
        if (issueSaveRequest.getMilestoneId() != null) {
            Milestone milestone = milestoneRepository.findById(issueSaveRequest.getMilestoneId())
                .orElseThrow(IllegalStateException::new);
            issue.updateMilestone(milestone);
        }

        List<Member> managers = memberRepository.findAllById(issueSaveRequest.getManagerIds());
        for (Member manager : managers) {
            IssueManager issueManager = new IssueManager(issue, manager);
        }
        List<Label> labels = labelRepository.findAllById(issueSaveRequest.getLabelIds());
        for (Label label : labels) {
            IssueLabel issueLabel = new IssueLabel(issue, label);
        }
        issueRepository.save(issue);
    }

    public IssueSearchResponse search(IssueSearchRequest issueSearchRequest) {
        List<Issue> issues = issueRepository.search(issueSearchRequest, null);
        long reverseStatusCount = issueRepository.searchReverseStatusCount(issueSearchRequest, null);
        if (issueSearchRequest.getStatus().equals(Status.OPEN)) {
            return new IssueSearchResponse(issues, issues.size(), reverseStatusCount);
        }
        return new IssueSearchResponse(issues, reverseStatusCount, issues.size());
    }

    public IssueDetailResponse findIssue(Long issueId, Long memberId) {
        Issue issue = findByIdOrThrow(issueId);
        Member requestMember = memberRepository.findById(memberId)
                .orElseThrow(IllegalStateException::new);
        return new IssueDetailResponse(issue, requestMember);
    }

    @Transactional
    public void editTitle(Long id, String title) {
        Issue issue = findByIdOrThrow(id);
        issue.updateTitle(title);
    }

    @Transactional
    public void delete(Long id) {
        Issue issue = findByIdOrThrow(id);
        issueRepository.delete(issue);
    }

    @Transactional
    public void editStatus(List<Long> issueIds, Status status) {
        List<Issue> issues = issueRepository.findAllById(issueIds);
        for (Issue issue : issues) {
            issue.updateStatus(status);
        }
    }

    private Issue findByIdOrThrow(Long id) {
        return issueRepository.findById(id).orElseThrow(IllegalStateException::new);
    }
}
