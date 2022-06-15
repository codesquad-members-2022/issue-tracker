package kr.codesquad.issuetraker.domain.milestone;

import kr.codesquad.issuetraker.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "milestone_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User Author;
    private String title;
    private String description;
    private LocalDate dueDate;
}
