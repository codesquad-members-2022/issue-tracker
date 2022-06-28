package com.ron2ader.issuetracker.domain.milestone;

import com.ron2ader.issuetracker.domain.issue.Issue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate endDate;

    @OneToMany(mappedBy = "milestone")
    private List<Issue> issues = new ArrayList<>();

    public static Milestone of(String title, String description, LocalDate endDate) {
        return new Milestone(null, title, description, endDate, null);
    }

    public Long countOpenIssue() {
        return issueCountByOpenStatus(true);
    }

    public Long countClosedIssue() {
        return issueCountByOpenStatus(false);
    }

    public void update(String title, String description, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.endDate = endDate;
    }

    private Long issueCountByOpenStatus(Boolean openStatus) {
        return issues.stream()
                .filter(issue -> issue.getOpenStatus() == openStatus)
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Milestone milestone = (Milestone) o;
        return Objects.equals(id, milestone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
