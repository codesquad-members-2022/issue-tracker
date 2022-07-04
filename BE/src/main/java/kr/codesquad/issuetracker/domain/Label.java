package kr.codesquad.issuetracker.domain;

import kr.codesquad.issuetracker.dto.LabelRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String backgroundColor;

    private String textColor;

    @OneToMany(mappedBy = "label")
    private List<IssueLabels> issueLabelsList = new ArrayList<>();

    public Label(LabelRequest labelRequest) {
        this.title = labelRequest.getTitle();
        this.content = labelRequest.getDescription();
        this.backgroundColor = labelRequest.getColor();
        this.textColor = labelRequest.getTextColor();
    }

    //TODO: 리팩토링 필요
    public void update(LabelRequest labelRequest){
        if (labelRequest.getTitle() != null) {
            this.title = labelRequest.getTitle();
        }
        if (labelRequest.getDescription() != null) {
            this.content = labelRequest.getDescription();
        }
        if (labelRequest.getColor() != null) {
            this.backgroundColor = labelRequest.getColor();
        }
        if (labelRequest.getTextColor() != null) {
            this.textColor = labelRequest.getTextColor();
        }
    }
}
