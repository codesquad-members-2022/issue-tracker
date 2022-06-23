package codesquad.issuetracker.domain;

import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "issue")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Issue extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;

    @OneToMany(mappedBy = "issue")
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "issue")
    private List<Reply> replies = new ArrayList<>();

    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
    private List<Assignee> assignees = new ArrayList<>();

    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
    private List<IssueLabel> issueLabels = new ArrayList<>();

    private String subject;
    private String description;

    @Enumerated(EnumType.STRING)
    private IssueStatus status;

    public boolean hasSameStatus(IssueStatus status) {
        return this.status.equals(status);
    }

    public List<Member> assignees() {
        return assignees
            .stream()
            .map(Assignee::getMember)
            .collect(Collectors.toList());
    }

    public List<Label> labels() {
        return issueLabels
            .stream()
            .map(IssueLabel::getLabel)
            .collect(Collectors.toList());
    }
}
