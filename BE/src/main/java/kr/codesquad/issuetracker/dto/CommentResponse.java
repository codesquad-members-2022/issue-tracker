package kr.codesquad.issuetracker.dto;

import kr.codesquad.issuetracker.domain.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {

    private Long id;
    private String content;
    private LocalDateTime createdTime;
    private String userId;
    private String imageUrl;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdTime = comment.getCreatedTime();
        this.userId = comment.getMember().getGithubId();
        this.imageUrl = comment.getMember().getImageUrl();
    }
}
