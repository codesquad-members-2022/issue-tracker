package louie.hanse.issuetracker.domain;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn
    private Issue issue;

    @ManyToOne(fetch = LAZY)
    @JoinColumn
    private Member writer;

    private String contents;
    private LocalDateTime createdDateTime = LocalDateTime.now();
    private LocalDateTime updatedDateTime = LocalDateTime.now();

    public Comment(Issue issue, String contents) {
        this.issue = issue;
        issue.addComment(this);
        this.contents = contents;
    }

    public void updateContents(String contents) {
        this.contents = contents;
        updatedDateTime = LocalDateTime.now();
    }
}
