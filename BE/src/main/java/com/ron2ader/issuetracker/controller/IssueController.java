package com.ron2ader.issuetracker.controller;

import com.ron2ader.issuetracker.auth.Login;
import com.ron2ader.issuetracker.controller.issuedto.IssueCreateRequest;
import com.ron2ader.issuetracker.controller.issuedto.IssueDetailResponse;
import com.ron2ader.issuetracker.controller.issuedto.IssueSimpleResponse;
import com.ron2ader.issuetracker.controller.issuedto.IssuesResponse;
import com.ron2ader.issuetracker.service.IssueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    /*
    * id만 반환할지, dto로 변환해서 저장된 내용 다시 보내줄지 상의 필요
    * */
    @PostMapping("/issues")
    public Long register(@Login String issuerId, @RequestBody IssueCreateRequest issueCreateRequest) {

        return issueService.registerIssue(issuerId,
                issueCreateRequest.getTitle(),
                issueCreateRequest.getContents(),
                issueCreateRequest.getAssigneeIds(),
                issueCreateRequest.getLabelIds(),
                issueCreateRequest.getMilestoneId());
    }

    @GetMapping("/issues/{issueNumber}")
    public IssueDetailResponse showIssue(@PathVariable Long issueNumber) {
        IssueDetailResponse issueDetailResponse = issueService.findById(issueNumber);

        return issueDetailResponse;
    }

    @GetMapping("/issues")
    public IssuesResponse showIssuesByOpenStatus(Pageable pageable, Boolean openStatus) {

        Page<IssueSimpleResponse> issues = issueService.findByOpenStatus(pageable, openStatus);
        Long countByStatus = issueService.countByStatus(!openStatus);

        if (openStatus) {
            return new IssuesResponse(issues.getTotalElements(), countByStatus, issues);
        }
        return new IssuesResponse(countByStatus, issues.getTotalElements(), issues);
    }
}
