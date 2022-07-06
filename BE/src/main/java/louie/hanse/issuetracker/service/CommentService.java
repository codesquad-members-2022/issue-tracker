package louie.hanse.issuetracker.service;

import lombok.RequiredArgsConstructor;
import louie.hanse.issuetracker.domain.Comment;
import louie.hanse.issuetracker.domain.Issue;
import louie.hanse.issuetracker.repository.CommentRepository;
import louie.hanse.issuetracker.web.dto.comment.CommentSaveResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final IssueService issueService;

    @Transactional
    public CommentSaveResponse write(Long issueId, String contents) {
        Issue issue = issueService.findByIdOrThrow(issueId);
        Comment comment = new Comment(issue, contents);
        commentRepository.save(comment);
        return new CommentSaveResponse(comment);
    }
}
