package com.ron2ader.issueTracker.domain.issue;

import com.ron2ader.issueTracker.domain.member.Member;

public class Issue {

    private Member issuer;

    private Integer issueNumber;

    private String title;

    private String contents;

    private String attachmentUrl;

    private Boolean isOpen;

}
