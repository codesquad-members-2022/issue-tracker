package codesquad.issuetracker.domain;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    @NotNull
    private Writer writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "milestone_id")
    private MileStone mileStone;

    @OneToMany(mappedBy = "issue")
    private List<Assignee> assignees = new ArrayList<>();

    @OneToMany(mappedBy = "issue")
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "issue")
    private List<Reply> replies = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "issue_label",
        joinColumns = @JoinColumn(name="issue_id"),
        inverseJoinColumns = @JoinColumn(name="label_id")
    )
    private List<Label> labels = new ArrayList<>();

    private String subject;
    private String description;

    @Enumerated(EnumType.STRING)
    private IssueStatus status;

    @Column(name = "created_datetime")
    private LocalDateTime createdDateTime;
    @Column(name = "updated_datetime")
    private LocalDateTime updatedDateTime;

}
