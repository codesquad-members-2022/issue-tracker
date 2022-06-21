package com.team33.backend.issue.controller.dto.label;

import lombok.Getter;

@Getter
public class LabelEditRequest {

    private String name;
    private String description;
    private String color;

    public LabelEditRequest(String name, String description, String color) {
        this.name = name;
        this.description = description;
        this.color = color;
    }

    public LabelEditRequest() {
    }
}
