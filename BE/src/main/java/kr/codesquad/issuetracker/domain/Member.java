package kr.codesquad.issuetracker.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor
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

    @Builder
    public Member(Long id, String name, String email, String githubId, String imageUrl, List<Issue> issueList, List<Comment> commentList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.githubId = githubId;
        this.imageUrl = imageUrl;
        this.issueList = issueList;
        this.commentList = commentList;
    }
}
