package kr.codesquad.issuetracker.service;

import kr.codesquad.issuetracker.domain.Issue;
import kr.codesquad.issuetracker.domain.IssueLabels;
import kr.codesquad.issuetracker.domain.IssueMembers;
import kr.codesquad.issuetracker.dto.*;
import kr.codesquad.issuetracker.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;

    public List<IssueResponse> getOpenedIssue(){
        return issueRepository.findOpenedIssues()
                .stream().map(IssueResponse::new).collect(Collectors.toList());
    }

    public ResponseEntity editStatus(IssueStatusRequest issueStatusRequest, Boolean status) {
        List<Long> issueList = issueStatusRequest.getIssueList();
        issueRepository.updateStatus(status, issueList);
        return new ResponseEntity(HttpStatus.OK);
    }

    //TODO: 리팩토링 필요..
    public IssueDetailResponse getIssueDetail(Long id){
        Issue issue = issueRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return IssueDetailResponse.builder()
                .issue(issue)
                .labels(issue.getIssueLabelsList().stream().map(IssueLabels::getLabel).map(LabelResponse::new).collect(Collectors.toList()))
                .milestone(new MilestoneResponse(issue.getMilestone()))
                .comments(issue.getCommentList().stream().map(CommentResponse::new).collect(Collectors.toList()))
                .writer(new MemberResponse(issue.getMember()))
                .assignees(issue.getIssueMembersList().stream().map(IssueMembers::getMember).map(AssigneeResponse::new).collect(Collectors.toList()))
                .build();
    }
}
