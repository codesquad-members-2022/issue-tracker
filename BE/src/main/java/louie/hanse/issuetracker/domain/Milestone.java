package louie.hanse.issuetracker.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Milestone {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "milestone")
    private List<Issue> issues = new ArrayList<>();

    private String title;

    @Column(name = "milestone_description")
    private String description;
    private LocalDate completedDate;

    public void addIssue(Issue issue) {
        this.issues.add(issue);
    }

    public Milestone(String title, String description, LocalDate completedDate) {
        this.title = title;
        this.description = description;
        this.completedDate = completedDate;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateCompletedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
    }
}
