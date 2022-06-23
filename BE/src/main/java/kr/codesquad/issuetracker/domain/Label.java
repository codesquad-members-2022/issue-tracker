package kr.codesquad.issuetracker.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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
}
