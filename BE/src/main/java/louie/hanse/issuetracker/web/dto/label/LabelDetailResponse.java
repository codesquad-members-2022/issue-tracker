package louie.hanse.issuetracker.web.dto.label;

import lombok.Getter;
import louie.hanse.issuetracker.domain.Label;
import louie.hanse.issuetracker.domain.TextColor;

@Getter
public class LabelDetailResponse {
    private long id;
    private String name;
    private String backgroundColor;
    private String description;
    private TextColor textColor;

    public LabelDetailResponse(Label label) {
        this.id = label.getId();
        this.name = label.getName();
        this.backgroundColor = label.getBackgroundColor();
        this.description = label.getDescription();
        this.textColor = label.getTextColor();
    }
}
