package louie.hanse.issuetracker.web.dto.label;

import lombok.Getter;
import louie.hanse.issuetracker.domain.Label;
import louie.hanse.issuetracker.domain.TextColor;

@Getter
public class LabelResponse {
    private String name;
    private String backgroundColor;
    private TextColor textColor;

    public LabelResponse(Label label) {
        this.name = label.getName();
        this.backgroundColor = label.getBackgroundColor();
        this.textColor = label.getTextColor();
    }
}
