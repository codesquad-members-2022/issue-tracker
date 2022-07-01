package kr.codesquad.issuetracker.web.dto.comment;

import java.time.LocalDateTime;
import kr.codesquad.issuetracker.domain.comment.Comment;
import kr.codesquad.issuetracker.domain.issue.Issue;
import kr.codesquad.issuetracker.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

	private Long id;
	private String userName;
	private String comment;
	private LocalDateTime createdDateTime;
	private String avatarUrl;
	private boolean isWriter;

	public static CommentResponseDto create(Issue issue, Member member, Comment comment) {
		return new CommentResponseDto(
			comment.getId(),
			comment.getWriter().getName(),
			comment.getContent(),
			comment.getCreatedDateTime(),
			comment.getWriter().getImageUrl(),
			comment.isWriter(member, issue)
		);
	}
}
