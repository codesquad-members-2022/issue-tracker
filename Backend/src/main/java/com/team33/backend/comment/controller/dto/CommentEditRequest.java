package com.team33.backend.comment.controller.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CommentEditRequest {

    @NotBlank
    private String content;

    public CommentEditRequest(String content) {
        this.content = content;
    }

    public CommentEditRequest() {
    }
}
