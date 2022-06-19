package louie.hanse.issuetracker.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class IssueManager {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Issue issue;

    @ManyToOne
    @JoinColumn
    private Member manger;
}
