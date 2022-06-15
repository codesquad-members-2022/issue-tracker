package codesquad.issuetracker.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "writer")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Writer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "writer_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    private String identity;
    private String password;
    private String email;
    private String nickname;
    private String profileUrl;

    @OneToMany(mappedBy = "writer")
    private List<Issue> issues = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    private List<Reply> replies = new ArrayList<>();

    @OneToOne(mappedBy = "writer", fetch = FetchType.LAZY)
    private Assignee assignee;

}
