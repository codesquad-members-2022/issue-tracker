package com.team33.backend.issue.controller.dto.label;

import lombok.Getter;

@Getter
public class LabelDeleteResponse {

    private final Long id;

    public LabelDeleteResponse(Long id) {
        this.id = id;
    }
}
