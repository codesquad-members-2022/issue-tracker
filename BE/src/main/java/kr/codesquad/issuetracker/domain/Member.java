package kr.codesquad.issuetracker.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "members_id")
    private Long id;

    private String name;

    private String email;

    private String githubId;

    private String imageUrl;

    @OneToMany(mappedBy = "member")
    private List<Issue> issueList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> commentList = new ArrayList<>();
}
