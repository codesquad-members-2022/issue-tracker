package kr.codesquad.issuetracker.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "label_id")
    private Long id;

    private String title;

    private String content;

    private String backgroundColor;

    private String textColor;

    @OneToMany(mappedBy = "label")
    private List<IssueLabels> issueLabelsList = new ArrayList<>();
}
