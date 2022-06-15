package com.ron2ader.issuetracker.controller.issuedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class IssueCreateRequest {

    private String title;
    private String contents;
    private MultipartFile attachmentFile;
}
