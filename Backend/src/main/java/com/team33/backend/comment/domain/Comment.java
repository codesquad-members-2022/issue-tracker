package com.team33.backend.comment.domain;

import com.team33.backend.common.jpa.entity.CommonEntity;
import com.team33.backend.common.jpa.entity.Deleted;
import com.team33.backend.emoji.domain.Emoji;
import com.team33.backend.image.domain.Image;
import com.team33.backend.issue.domain.Issue;
import com.team33.backend.member.domain.Member;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
public class Comment extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @NotBlank
    private String content;


    @Embedded
    private Deleted deleted;

    @OneToMany(mappedBy = "comment", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Image> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id", nullable = false)
    private Issue issue;

    @OneToMany(mappedBy = "comment", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Emoji> emojis = new HashSet<>();

    public Comment(String content) {
        this.content = content;
        this.deleted = initDeleted();
    }

    public Comment(String content, Member member, Issue issue) {
        validateContent(content);
        this.content = content;
        validateMember(member);
        this.member = member;
        validateIssue(issue);
        this.issue = issue;
        this.deleted = initDeleted();
    }

    protected Comment() {
    }

    private Deleted initDeleted() {
        return new Deleted();
    }

    public void registMember(Member member){
        this.member = member;
    }

    public void registIssue(Issue issue){
        this.issue = issue;
    }

    private void validateContent(String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("내용을 입력해주세요.");
        }
    }

    private void validateMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("회원정보가 존재하지 않습니다.");
        }
    }

    private void validateIssue(Issue issue) {
        if (issue == null) {
            throw new IllegalArgumentException("이슈정보가 존재하지 않습니다.");
        }
    }

    public void editComment(String content) {
        validateContent(content);
        this.content = content;
    }

    public void addImage(Image image) {
        if (image != null) {
            this.images.add(image);
            image.addImageToComment(this);
        }
    }

    public void addImages(List<Image> images) {
        for (Image image : images) {
            if (image != null) {
                this.images.add(image);
                image.addImageToComment(this);
            }
        }
    }

    public void deleteComment() {
        deleted.isTrue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment entity = (Comment) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
