package kr.codesquad.issuetracker.domain;

import kr.codesquad.issuetracker.dto.MilestoneRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDate deadline;

    @OneToMany(mappedBy = "milestone")
    private List<Issue> issueList = new ArrayList<>();

    public Milestone(MilestoneRequest milestoneRequest) {
        this.title = milestoneRequest.getTitle();
        this.content = milestoneRequest.getDescription();
        this.deadline = milestoneRequest.getDeadline();
    }
}
