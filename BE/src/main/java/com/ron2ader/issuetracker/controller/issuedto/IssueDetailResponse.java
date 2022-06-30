package com.ron2ader.issuetracker.controller.issuedto;

import com.ron2ader.issuetracker.controller.labeldto.LabelResponse;
import com.ron2ader.issuetracker.controller.memberdto.MemberDto;
import com.ron2ader.issuetracker.controller.milestonedto.MilestoneResponse;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
public class IssueDetailResponse {

    private final MemberDto issuer;
    private final IssueDetail issueDetail;
    private final List<MemberDto> assignees;
    private final List<LabelResponse> labels;
    private final MilestoneResponse milestone;


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
