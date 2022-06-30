package louie.hanse.issuetracker.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.*;

@Getter
@Entity
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
}
