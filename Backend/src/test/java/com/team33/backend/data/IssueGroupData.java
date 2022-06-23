package com.team33.backend.data;

import com.team33.backend.issuegroup.domain.IssueGroup;
import lombok.Getter;

@Getter
public class IssueGroupData {

    private IssueGroup issueGroup;

    public IssueGroupData(){
        this.issueGroup = createIssueGroup();
    }

    private IssueGroup createIssueGroup() {
        return new IssueGroup("Issue-Tracker-Team33");
    }
}
