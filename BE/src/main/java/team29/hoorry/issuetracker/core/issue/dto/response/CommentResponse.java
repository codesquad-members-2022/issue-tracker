package team29.hoorry.issuetracker.core.issue.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team29.hoorry.issuetracker.core.issue.domain.Comment;
import team29.hoorry.issuetracker.core.issue.domain.CommentReaction;
import team29.hoorry.issuetracker.core.member.domain.Member;
import team29.hoorry.issuetracker.core.member.dto.MemberResponse;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

	private MemberResponse writer;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String content;
	private List<ReactionResponse> reactions;

	public static CommentResponse from(Comment comment, List<CommentReaction> commentReactions) {
		Map<String, List<Member>> membersGroupByReaction = groupingByReaction(commentReactions);

		List<ReactionResponse> reactions = new ArrayList<>();
		membersGroupByReaction.forEach((emoji, members) -> reactions.add(ReactionResponse.from(emoji, members)));

		return new CommentResponse(
			MemberResponse.from(comment.getWriter()),
			comment.getCreatedAt(),
			comment.getUpdatedAt(),
			comment.getContent(),
			reactions
		);
	}

	private static Map<String, List<Member>> groupingByReaction(List<CommentReaction> commentReactions) {
		Map<String, List<Member>> membersGroupByReaction = new HashMap<>();
		for (CommentReaction commentReaction : commentReactions) {
			String emoji = commentReaction.getReaction().getEmoji();

			if (!membersGroupByReaction.containsKey(emoji)) {
				membersGroupByReaction.put(emoji, new ArrayList<>());
			}
			membersGroupByReaction.get(emoji).add(commentReaction.getMember());
		}
		return membersGroupByReaction;
	}
}
