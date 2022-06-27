package louie.hanse.issuetracker.repository;

import louie.hanse.issuetracker.domain.Comment;
import louie.hanse.issuetracker.domain.QMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
