package kr.codesquad.issuetraker.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "user_id")
    private Long id;
    private String email;
    private String displayName;
    private String password;
    private String profileImageUrl;
    @Enumerated(EnumType.STRING)
    private OauthClient oauthClient;
}
