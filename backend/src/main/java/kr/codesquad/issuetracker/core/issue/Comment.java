package kr.codesquad.issuetracker.core.issue;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import kr.codesquad.issuetracker.common.BaseTimeEntity;
import kr.codesquad.issuetracker.core.user.User;

@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Issue issue;
    @ManyToOne
    private User user;
    private String content;
    private boolean editable;
    @OneToMany(mappedBy = "comment")
    private List<AttachedFile> attachedFiles;

}
