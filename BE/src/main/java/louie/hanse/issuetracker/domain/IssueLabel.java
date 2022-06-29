package louie.hanse.issuetracker.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@NoArgsConstructor
public class IssueLabel {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn
    private Issue issue;

    @ManyToOne(fetch = LAZY)
    @JoinColumn
    private Label label;

    public IssueLabel(Issue issue, Label label) {
        this.issue = issue;
        issue.addIssueLabel(this);
        this.label = label;
        label.addIssueLabel(this);
    }
}
