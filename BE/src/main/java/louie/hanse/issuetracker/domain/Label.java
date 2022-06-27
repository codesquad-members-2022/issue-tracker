package louie.hanse.issuetracker.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.*;

@Entity
public class Label {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "issue")
    private List<IssueLabel> issueLabels = new ArrayList<>();

    @Column(name = "label_name")
    private String name;

    @Column(name = "label_description")
    private String description;
    private String backgroundColor;
    private String textColor;

    public void addIssueLabel(IssueLabel issueLabel) {
        this.issueLabels.add(issueLabel);
    }
}
