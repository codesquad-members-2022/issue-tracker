package louie.hanse.issuetracker.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.*;

@Entity
public class Milestone {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "milestone")
    private List<Issue> issue = new ArrayList<>();

    private String title;

    @Column(name = "milestone_description")
    private String description;
    private LocalDate completedDate;

    @Enumerated(EnumType.STRING)
    private Status status;
}
