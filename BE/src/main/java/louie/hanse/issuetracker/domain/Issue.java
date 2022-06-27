package louie.hanse.issuetracker.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @ManyToOne(fetch = LAZY)
    @JoinColumn
    private Member writer;

    @OneToMany(mappedBy = "issue")
    private List<IssueManager> issueManagers = new ArrayList<>();

    private String title;
    private LocalDateTime createDateTime = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Status status = Status.OPEN;

    public Issue(String title, Member writer) {
        this.title = title;
        this.writer = writer;
    }

    public void updateMilestone(Milestone milestone) {
        this.milestone = milestone;
        milestone.addIssue(this);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addIssueManager(IssueManager issueManager) {
        this.issueManagers.add(issueManager);
    }

    public void addIssueLabel(IssueLabel issueLabel) {
        this.issueLabels.add(issueLabel);
    }
}
