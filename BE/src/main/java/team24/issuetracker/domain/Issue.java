package team24.issuetracker.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User writer;

    @OneToMany(mappedBy = "issue")
    private List<IssueUser> assignees;

    @OneToMany(mappedBy = "issue")
    private List<IssueLabel> issueLabels;

    @ManyToOne(fetch = FetchType.LAZY)
    private Milestone milestone;
    private boolean isClosed;

    @OneToMany(mappedBy = "issue")
    private List<Image> images;
    private LocalDateTime writtenTime;

    @OneToMany(mappedBy = "issue")
    private List<Comment> comments;

    private boolean isDeleted;
}
