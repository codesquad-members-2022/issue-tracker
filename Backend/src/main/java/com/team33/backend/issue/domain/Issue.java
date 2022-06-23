package com.team33.backend.issue.domain;

import com.team33.backend.comment.domain.Comment;
import com.team33.backend.common.jpa.entity.CommonEntity;
import com.team33.backend.common.jpa.entity.Deleted;
import com.team33.backend.emoji.domain.Emoji;
import com.team33.backend.issuegroup.domain.IssueGroup;
import com.team33.backend.member.domain.Member;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
public class Issue extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    @NotBlank
    private String title;

    @Embedded
    private Deleted deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Member author;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IssueStatus issueStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_group_id", nullable = false)
    private IssueGroup issueGroup;

    @OneToMany(mappedBy = "issue")
    private List<Assignee> assignees = new ArrayList<>();

    @OneToMany(mappedBy = "issue", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "issue", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Emoji> emojis = new ArrayList<>();

    public Issue(String title, Member author, IssueGroup issueGroup) {
        this.title = title;
        this.author = author;
        this.issueStatus = initIssueStatus();
        this.issueGroup = issueGroup;
    }

    public Issue(String title) {
        this.title = title;
        this.issueStatus = initIssueStatus();
        this.deleted = initDeleted();
    }

    private Deleted initDeleted() {
        return new Deleted();
    }

    public void registAuthor(Member author) {
        this.author = author;
    }

    public void registIssueGroup(IssueGroup issueGroup) {
        this.issueGroup = issueGroup;
    }

    protected Issue() {

    }

    private IssueStatus initIssueStatus() {
        return IssueStatus.OPEN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Issue)) return false;
        Issue entity = (Issue) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
