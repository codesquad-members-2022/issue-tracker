package louie.hanse.issuetracker.web.dto;

import louie.hanse.issuetracker.domain.Label;

public class LabelResponse {
    private String name;
    private String backgroundColor;
    private String textColor;

    public LabelResponse(Label label) {
        this.name = label.getName();
        this.backgroundColor = label.getBackgroundColor();
        this.textColor = label.getTextColor();
    }
}
