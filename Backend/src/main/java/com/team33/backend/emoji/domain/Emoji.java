package com.team33.backend.emoji.domain;

import com.team33.backend.comment.domain.Comment;
import com.team33.backend.issue.domain.Issue;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
@Getter
public class Emoji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4)
    private String emotion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id", nullable = false)
    private Issue issue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Emoji)) return false;
        Emoji entity = (Emoji) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void registComment(Comment comment) {
        if (comment != null) {
            this.comment = comment;
        }
    }
}
