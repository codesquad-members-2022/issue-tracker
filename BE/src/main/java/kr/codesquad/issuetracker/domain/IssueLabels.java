package kr.codesquad.issuetracker.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class IssueLabels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issues_labels_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Issue issue;

    @ManyToOne(fetch = LAZY)
    private Label label;
}
