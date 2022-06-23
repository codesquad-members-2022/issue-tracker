package kr.codesquad.issuetracker.dto;

import lombok.Getter;

@Getter
public class LabelRequest {

    private String title;
    private String description;
    private String color;
    private String textColor;
}
