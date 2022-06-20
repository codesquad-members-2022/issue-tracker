package com.ron2ader.issuetracker.controller.issuedto;

import com.ron2ader.issuetracker.controller.memberdto.MemberDto;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IssueDetailResponse that = (IssueDetailResponse) o;
        return Objects.equals(issueDetail, that.issueDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issueDetail);
    }
}
