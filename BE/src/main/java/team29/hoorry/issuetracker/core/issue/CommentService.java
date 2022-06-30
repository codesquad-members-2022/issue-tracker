package team29.hoorry.issuetracker.core.issue;

import java.util.HashSet;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team29.hoorry.issuetracker.core.issue.domain.Comment;
import team29.hoorry.issuetracker.core.issue.domain.CommentReaction;
import team29.hoorry.issuetracker.core.issue.domain.Issue;
import team29.hoorry.issuetracker.core.issue.domain.Reaction;
import team29.hoorry.issuetracker.core.issue.dto.request.CommentSaveRequest;
import team29.hoorry.issuetracker.core.issue.dto.request.ReactionSaveRequest;
import team29.hoorry.issuetracker.core.member.MemberRepository;
import team29.hoorry.issuetracker.core.member.domain.Member;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

	private final CommentRepository commentRepository;
	private final IssueRepository issueRepository;
	private final MemberRepository memberRepository;
	private final CommentReactionRepository commentReactionRepository;

	public Long write(Long issueId, CommentSaveRequest commentSaveRequest) {
		Issue issue = issueRepository.findById(issueId)
			.orElseThrow(() -> new NoSuchElementException("해당 issueId를 가진 이슈는 없습니다."));
		Member writer = memberRepository.findById(commentSaveRequest.getMemberId())
			.orElseThrow(() -> new NoSuchElementException("해당 memberId를 가진 멤버는 없습니다."));
		Comment savedComment = commentRepository.save(Comment.of(issue, writer, commentSaveRequest.getContent(), new HashSet<>()));
		return savedComment.getId();
	}

	public Long addReaction(Long commentId, ReactionSaveRequest reactionSaveRequest) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new NoSuchElementException("해당 commentId를 가진 코멘트는 없습니다."));
		Member member = memberRepository.findById(reactionSaveRequest.getMemberId())
			.orElseThrow(() -> new NoSuchElementException("해당 memberId를 가진 멤버는 없습니다."));
		Reaction reaction = Reaction.findByEmoji(reactionSaveRequest.getEmoji());

		CommentReaction savedCommentReaction = commentReactionRepository.save(
			CommentReaction.of(comment, member, reaction));

		return savedCommentReaction.getId();
	}

	public void deleteReaction(Long commentId, ReactionSaveRequest reactionSaveRequest) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new NoSuchElementException("해당 commentId를 가진 코멘트는 없습니다."));
		Member member = memberRepository.findById(reactionSaveRequest.getMemberId())
			.orElseThrow(() -> new NoSuchElementException("해당 memberId를 가진 멤버는 없습니다."));
		Reaction reaction = Reaction.findByEmoji(reactionSaveRequest.getEmoji());

		commentReactionRepository.deleteByCommentAndMemberAndReaction(comment, member, reaction);
	}
}

