package com.ron2ader.issuetracker.controller.issuedto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class IssueCreateRequest {

    private String title;
    private String contents;
    private MultipartFile attachmentFile;
}
