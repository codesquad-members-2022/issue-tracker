package kr.codesquad.issuetracker.dto;

import kr.codesquad.issuetracker.domain.IssueLabels;
import kr.codesquad.issuetracker.domain.Label;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LabelResponse {

    private Long id;
    private String title;
    private String description;
    private String color;
    private String textColor;
    private List<IssueLabels> issueLabelsList;

    public LabelResponse(Label label) {
        this.id = label.getId();
        this.title = label.getTitle();
        this.description = label.getContent();
        this.color = label.getBackgroundColor();
        this.textColor = label.getTextColor();
        this.issueLabelsList = label.getIssueLabelsList();
    }
}
