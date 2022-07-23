package louie.hanse.issuetracker.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IssueManager {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Issue issue;

    @ManyToOne
    @JoinColumn
    private Member manager;

    public IssueManager(Issue issue, Member manager) {
        this.issue = issue;
        issue.addIssueManager(this);
        this.manager = manager;
    }
}
