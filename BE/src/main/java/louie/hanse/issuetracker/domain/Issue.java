package louie.hanse.issuetracker.domain;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Issue {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "issue")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "issue")
    private List<IssueLabel> issueLabels = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn
    private Milestone milestone;

    private String title;
    private LocalDateTime createDateTime;

    @Enumerated(EnumType.STRING)
    private Status status;

}
