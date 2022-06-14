package com.ron2ader.issuetracker.controller.issuedto;

import com.ron2ader.issuetracker.controller.memberdto.MemberDto;
import lombok.Getter;

@Getter
public class IssueDetailResponse {

    private MemberDto memberDto;
    private IssueDetail issueDetail;

    public IssueDetailResponse(MemberDto memberDto,
        IssueDetail issueDetail) {
        this.memberDto = memberDto;
        this.issueDetail = issueDetail;
    }
}
