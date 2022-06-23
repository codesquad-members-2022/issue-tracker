package com.team33.backend.member.domain;

import com.team33.backend.common.jpa.entity.Deleted;
import com.team33.backend.issue.domain.Issue;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 39)
    @NotBlank
    private String name;

    @NotBlank
    private String githubId;

    @Column(length = 2048)
    private String profileImageUrl;

    @Embedded
    private Deleted deleted;

    @OneToMany(mappedBy = "author")
    private List<Issue> issues = new ArrayList<>();

    public Member(String name) {
        this.name = name;
        this.deleted = new Deleted();
    }

    public Member(String githubId, String profileImageUrl) {
        this.githubId = githubId;
        this.profileImageUrl = profileImageUrl;
        this.deleted = new Deleted();
    }

    public Member(String name, String githubId, String profileImageUrl) {
        this.name = name;
        this.githubId = githubId;
        this.profileImageUrl = profileImageUrl;
        this.deleted = new Deleted();
    }

    public Member() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member entity = (Member) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
