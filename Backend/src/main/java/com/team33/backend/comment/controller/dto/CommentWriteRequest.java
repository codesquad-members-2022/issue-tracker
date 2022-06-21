package com.team33.backend.comment.controller.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CommentWriteRequest {

    private final Long issueId;
    @NotBlank
    private final String content;

    public CommentWriteRequest(Long issueId, String content) {
        this.issueId = issueId;
        this.content = content;
    }
}
