package louie.hanse.issuetracker.domain;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "comment")
    private List<UploadFile> uploadFiles = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn
    private Issue issue;

    private String content;
    private LocalDateTime createdDateTime;

}
