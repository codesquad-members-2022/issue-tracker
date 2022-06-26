package louie.hanse.issuetracker.repository;

import louie.hanse.issuetracker.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
