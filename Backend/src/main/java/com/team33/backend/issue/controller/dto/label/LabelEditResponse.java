package com.team33.backend.issue.controller.dto.label;

import com.team33.backend.issue.domain.Label;
import lombok.Getter;

@Getter
public class LabelEditResponse {

    private Long id;
    private String name;
    private String description;
    private String color;

    public LabelEditResponse(Label label) {
        this.id = label.getId();
        this.name = label.getName();
        this.description = label.getDescription();
        this.color = label.getColor();
    }

    public LabelEditResponse() {
    }
}
