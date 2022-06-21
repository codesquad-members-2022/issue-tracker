package com.team33.backend.issue.controller.dto.label;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class LabelCount {

    private long value;

    public LabelCount(long value) {
        this.value = value;
    }

    public LabelCount() {
    }
}
