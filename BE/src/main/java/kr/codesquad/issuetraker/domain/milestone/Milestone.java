package kr.codesquad.issuetraker.domain.milestone;

import kr.codesquad.issuetraker.domain.issue.Issue;
import kr.codesquad.issuetraker.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "milestone_id")
    private Long id;
    private String title;
    private String description;
    @OneToMany(mappedBy = "milestone")
    private List<Issue> issues = new ArrayList<>();
    private LocalDate dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
