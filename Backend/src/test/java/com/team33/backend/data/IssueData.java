package com.team33.backend.data;

import com.team33.backend.issue.domain.Issue;
import lombok.Getter;

@Getter
public class IssueData {

    private Issue issue;

    public IssueData (){
        this.issue = createIssue();
    }
    public Issue createIssue(){
        return new Issue("계획");
    }
}
