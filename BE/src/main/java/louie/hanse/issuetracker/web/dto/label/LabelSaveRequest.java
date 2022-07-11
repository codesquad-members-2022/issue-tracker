package louie.hanse.issuetracker.web.dto.label;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import louie.hanse.issuetracker.domain.Label;
import louie.hanse.issuetracker.domain.TextColor;

@Getter
public class LabelSaveRequest {

    @NotEmpty
    private String name;
    private String description;

    @NotEmpty
    private String backgroundColor;
    private TextColor textColor;

    public Label toEntity() {
        return new Label(name, description, backgroundColor, textColor);
    }
}
