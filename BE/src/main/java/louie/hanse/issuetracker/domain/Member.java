package louie.hanse.issuetracker.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String socialId;
    private String avatarImageUrl;

    public Member(String socialId, String avatarImageUrl) {
        this.socialId = socialId;
        this.avatarImageUrl = avatarImageUrl;
    }

    public Long getId() {
        return id;
    }
}
