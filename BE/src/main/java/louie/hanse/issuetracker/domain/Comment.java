package louie.hanse.issuetracker.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "comment")
    private List<UploadFile> uploadFiles = new ArrayList<>();

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
        this.contents = contents;
    }
}
