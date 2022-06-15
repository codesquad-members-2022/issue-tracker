package com.ron2ader.issuetracker.service;

import com.ron2ader.issuetracker.controller.issuedto.IssueCreateRequest;
import com.ron2ader.issuetracker.controller.issuedto.IssueDetail;
import com.ron2ader.issuetracker.controller.issuedto.IssueDetailResponse;
import com.ron2ader.issuetracker.controller.issuedto.IssueSimpleResponse;
import com.ron2ader.issuetracker.controller.memberdto.MemberDto;
import com.ron2ader.issuetracker.domain.issue.Issue;
import com.ron2ader.issuetracker.domain.issue.IssueRepository;

import java.util.NoSuchElementException;

import com.ron2ader.issuetracker.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueDetailResponse registerIssue(IssueCreateRequest issueCreateRequest, MemberDto issuer) {
        //TODO
        // s3에 파일을 보내고 url을 받는다

        Issue savedIssue = issueRepository.save(
                Issue.of(Member.of(issuer.getUserId(), issuer.getAvatarUrl()),
                        issueCreateRequest.getTitle(), issueCreateRequest.getContents(), null));

        return new IssueDetailResponse(issuer, IssueDetail.from(savedIssue));
    }

    // 상세 정보
    public IssueDetailResponse findById(Long issueNumber) {
        Issue targetIssue = issueRepository.findById(issueNumber)
                .orElseThrow(() -> new NoSuchElementException("해당하는 이슈가 없습니다."));

        return new IssueDetailResponse(MemberDto.from(targetIssue.getIssuer()), IssueDetail.from(targetIssue));
    }

    public Page<IssueSimpleResponse> findAllByOpenStatus(Pageable pageable, Boolean openStatus) {
        Page<Issue> issues = issueRepository.findAllByOpenStatus(pageable, openStatus);
        return issues.map(IssueSimpleResponse::from);
    }
}
