package team29.hoorry.issuetracker.core.issue;

import org.springframework.data.jpa.repository.JpaRepository;
import team29.hoorry.issuetracker.core.issue.domain.Comment;
import team29.hoorry.issuetracker.core.issue.domain.CommentReaction;
import team29.hoorry.issuetracker.core.issue.domain.Reaction;
import team29.hoorry.issuetracker.core.member.domain.Member;

public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long> {

	void deleteByCommentAndMemberAndReaction(Comment comment, Member member, Reaction reaction);
}
