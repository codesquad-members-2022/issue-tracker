package com.team33.backend.issue.controller.dto.milestone;

import lombok.Getter;

@Getter
public class MilestoneTitleEditRequest {

    private String title;

    public MilestoneTitleEditRequest(String title) {
        this.title = title;
    }

    public MilestoneTitleEditRequest() {
    }
}
