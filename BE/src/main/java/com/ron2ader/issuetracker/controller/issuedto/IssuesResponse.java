package com.ron2ader.issuetracker.controller.issuedto;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class IssuesResponse {

    private final Long openCount;
    private final Long closeCount;
    private final List<IssueSimpleResponse> issueSimpleResponse;
}
