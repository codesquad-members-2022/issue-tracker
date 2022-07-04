package kr.codesquad.issuetracker.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class IssueStatusRequest {

    private List<Long> issueList;
}
