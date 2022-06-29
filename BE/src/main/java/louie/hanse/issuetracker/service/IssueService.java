package louie.hanse.issuetracker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import louie.hanse.issuetracker.domain.*;
import louie.hanse.issuetracker.repository.*;
import louie.hanse.issuetracker.web.dto.IssueResponse;
import louie.hanse.issuetracker.web.dto.IssueSaveRequest;
import louie.hanse.issuetracker.web.dto.IssueSearchRequest;
import louie.hanse.issuetracker.web.dto.IssueSearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class IssueService {
    private final IssueRepository issueRepository;
    private final CommentRepository commentRepository;
    private final IssueManagerRepository issueManagerRepository;
    private final MemberRepository memberRepository;
    private final LabelRepository labelRepository;
    private final IssueLabelRepository issueLabelRepository;
    private final MilestoneRepository milestoneRepository;

    @Transactional
    public void register(IssueSaveRequest issueSaveRequest, Long memberId) {
        Member writer = memberRepository.findById(memberId)
            .orElseThrow(IllegalStateException::new);
        Issue issue = new Issue(issueSaveRequest.getTitle(), writer);
        log.info("issueSaveRequest {}", issueSaveRequest);
        issueRepository.save(issue);
        if (issueSaveRequest.getContents() != null) {
            Comment comment = new Comment(issue, issueSaveRequest.getContents());
            commentRepository.save(comment);
        }
        if (!issueSaveRequest.getManagerIds().isEmpty()) {
            List<Member> managers = memberRepository.findAllById(issueSaveRequest.getManagerIds());
            for (Member manager : managers) {
                IssueManager issueManager = new IssueManager(issue, manager);
                issueManagerRepository.save(issueManager);
            }
        }
        if (!issueSaveRequest.getLabelIds().isEmpty()) {
            List<Label> labels = labelRepository.findAllById(issueSaveRequest.getLabelIds());
            for (Label label : labels) {
                IssueLabel issueLabel = new IssueLabel(issue, label);
                issueLabelRepository.save(issueLabel);
            }
        }
        if (issueSaveRequest.getMilestoneId() != null) {
            Milestone milestone = milestoneRepository.findById(issueSaveRequest.getMilestoneId())
                .orElseThrow(IllegalStateException::new);
            issue.updateMilestone(milestone);
        }
    }

    public IssueSearchResponse search(IssueSearchRequest issueSearchRequest) {
        List<Issue> issues = issueRepository.search(issueSearchRequest, null);
        issues.stream().map(issue -> new IssueResponse(issue)).collect(Collectors.toList());
        return null;
    }
 }
