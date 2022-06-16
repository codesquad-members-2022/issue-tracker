package kr.codesquad.issuetracker.core.milestone;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import kr.codesquad.issuetracker.core.issue.Issue;

@Entity
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "milestone")
    private List<Issue> issue;

    private String title;
    private String description;
    private LocalDateTime deadline;
}
