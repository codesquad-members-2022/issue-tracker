package louie.hanse.issuetracker.web.dto;

import louie.hanse.issuetracker.domain.Comment;
import louie.hanse.issuetracker.domain.Member;

import java.time.LocalDateTime;

public class CommentResponse {
    private Long id;
    private String writerName;
    private String writerImageUrl;
    private LocalDateTime updatedDateTime;
    private String contents;
    private boolean isIssueWriter;
    private boolean isEdit;

    public CommentResponse(Comment comment, Member user) {
        this.id = comment.getId();
        this.writerName = comment.getWriter().getSocialId();
        this.writerImageUrl = comment.getWriter().getAvatarImageUrl();
        this.updatedDateTime = comment.getCreatedDateTime();
        this.contents = comment.getContents();
        this.isIssueWriter = comment.getIssue().getWriter().equals(comment.getWriter());
        this.isEdit = comment.getWriter().equals(user);
    }
}
