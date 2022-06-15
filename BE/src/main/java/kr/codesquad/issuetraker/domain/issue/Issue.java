package kr.codesquad.issuetraker.domain.issue;

import kr.codesquad.issuetraker.domain.label.Label;
import kr.codesquad.issuetraker.domain.milestone.Milestone;
import kr.codesquad.issuetraker.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_id")
    private Long id;
    @Column(name = "issue_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @ManyToMany
    @JoinTable(name = "issue_assignee",
            joinColumns = @JoinColumn(name = "issue_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> assignees;
    @ManyToMany
    @JoinTable(name = "issue_label",
            joinColumns = @JoinColumn(name = "issue_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id"))
    private List<Label> labels;
    private String title;
    private String description;
    private boolean isOpened;
    private LocalDateTime createdAt;
}
