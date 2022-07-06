package louie.hanse.issuetracker.web.dto.comment;

import java.time.LocalDateTime;
import lombok.Getter;
import louie.hanse.issuetracker.domain.Comment;

@Getter
public class CommentSaveResponse {
    private Long id;
    private LocalDateTime createDateTime;

    public CommentSaveResponse(Comment comment) {
        this.id = comment.getId();
        this.createDateTime = comment.getCreatedDateTime();
    }
}
