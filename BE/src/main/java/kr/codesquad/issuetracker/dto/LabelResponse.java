package kr.codesquad.issuetracker.dto;

import kr.codesquad.issuetracker.domain.Label;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LabelResponse {

    private Long id;
    private String title;
    private String description;
    private String color;
    private String textColor;

    public LabelResponse(Long id, String title, String color, String textColor, String description) {
        this.id = id;
        this.title = title;
        this.color = color;
        this.textColor = textColor;
        this.description = description;
    }

    public LabelResponse(Label label) {
        this.id = label.getId();
        this.title = label.getTitle();
        this.description = label.getContent();
        this.color = label.getBackgroundColor();
        this.textColor = label.getTextColor();
    }
}
