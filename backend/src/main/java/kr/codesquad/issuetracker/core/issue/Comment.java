package kr.codesquad.issuetracker.core.issue;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import kr.codesquad.issuetracker.core.user.User;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Issue issue;
    @ManyToOne
    private User user;
    private String content;
    private boolean editable;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "comment")
    private List<AttachedFile> attachedFiles;

}
