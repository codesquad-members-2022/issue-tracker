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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private User writer;

    @OneToMany(mappedBy = "issue")
    @JsonManagedReference
    private List<Assignee> assignees;

    @OneToMany(mappedBy = "issue")
    @JsonManagedReference
    private List<IssueLabel> issueLabels;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Milestone milestone;
    private boolean isClosed;

    @OneToMany(mappedBy = "issue")
    @JsonManagedReference
    private List<Image> images;
    private LocalDateTime writtenTime;

    @OneToMany(mappedBy = "issue")
    @JsonManagedReference
    private List<Comment> comments;

    private boolean isDeleted;
}
