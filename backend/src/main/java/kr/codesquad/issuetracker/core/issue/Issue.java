package kr.codesquad.issuetracker.core.issue;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import kr.codesquad.issuetracker.core.label.Label;
import kr.codesquad.issuetracker.core.milestone.Milestone;
import kr.codesquad.issuetracker.core.user.User;

@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    private User user;
    @Column(name = "open_yn")
    private boolean isOpen;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @ManyToOne
    private Milestone milestone;
    @OneToMany(mappedBy = "issue")
    private List<Label> labels;
    @OneToMany(mappedBy = "issue")
    private List<Assignee> assignees;
    @OneToMany(mappedBy = "issue")
    private List<Comment> comments;
}
