package com.team33.backend.data;

import com.team33.backend.comment.domain.Comment;
import com.team33.backend.issue.domain.Issue;
import com.team33.backend.issuegroup.domain.IssueGroup;
import com.team33.backend.member.domain.Member;
import lombok.Getter;

@Getter
public class CommentData {

    private Comment comment;

    public CommentData (){
        this.comment = createComment();
    }
    private Comment createComment() {
        if (this.comment == null) {
            this.comment = new Comment("내용");
        }
        return comment;
    }

    private Issue createIsue() {
        return new Issue("Backend", createMember(), createIssueGroup());
    }

    private Member createMember() {
        return new Member("Jun");
    }

    private IssueGroup createIssueGroup() {
        return new IssueGroup("Issue-Tracker");
    }
}
