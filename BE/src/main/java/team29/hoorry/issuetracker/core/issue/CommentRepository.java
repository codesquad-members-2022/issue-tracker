package team29.hoorry.issuetracker.core.issue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team29.hoorry.issuetracker.core.issue.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
