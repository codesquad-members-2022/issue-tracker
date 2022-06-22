package com.team33.backend.issue.domain;

import com.team33.backend.common.jpa.entity.CommonEntity;
import com.team33.backend.group.IssueGroup;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
public class Milestone extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    @NotBlank
    private String title;

    @Column(length = 8192)
    private String description;

    private LocalDate deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_group_id", nullable = false)
    private IssueGroup issueGroup;

    public Milestone(String title, String description, LocalDate deadline) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
    }

    protected Milestone() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Milestone)) return false;
        Milestone entity = (Milestone) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void editMilestone(String title, String description, LocalDate deadline) {
        editTitle(title);
        editDescription(description);
        editDeadline(deadline);
    }

    private void editTitle(String title) {
        if (title != null && title.length() >= 1) {
            this.title = title;
            return;
        }
        throw new IllegalArgumentException("제목을 입력해주세요.");
    }

    private void editDescription(String description) {
        if (description != null && description.length() >= 1) {
            this.description = description;
            return;
        }
        throw new IllegalArgumentException("내용을 입력해주세요.");
    }

    private void editDeadline(LocalDate deadline) {
        if (deadline != null) {
            this.deadline = deadline;
            return;
        }
        throw new IllegalArgumentException("마감일을 입력해주세요.");
    }

    public void delete() {
    }
}
