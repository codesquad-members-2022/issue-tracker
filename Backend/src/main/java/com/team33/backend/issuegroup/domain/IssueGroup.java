package com.team33.backend.issuegroup.domain;

import com.team33.backend.common.jpa.entity.CommonEntity;
import com.team33.backend.issue.domain.Milestone;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
public class IssueGroup extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "issueGroup", cascade = CascadeType.REMOVE)
    private List<Milestone> milestones = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IssueGroup)) return false;
        IssueGroup entity = (IssueGroup) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
