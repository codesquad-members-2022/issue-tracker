package kr.codesquad.issuetracker.core.issue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import kr.codesquad.issuetracker.core.user.User;

@Entity
public class Responsibility {

    @Id
    @GeneratedValue
    private String id;
    @ManyToOne
    private Issue issue;
    @ManyToOne
    private User user;

}
