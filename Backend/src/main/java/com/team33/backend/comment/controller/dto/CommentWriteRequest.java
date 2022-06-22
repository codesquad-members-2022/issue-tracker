package com.team33.backend.comment.controller.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CommentWriteRequest {

    @NotBlank
    private final String content;

    public CommentWriteRequest(String content) {
        this.content = content;
    }
}
