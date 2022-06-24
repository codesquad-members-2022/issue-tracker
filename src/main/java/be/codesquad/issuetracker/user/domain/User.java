package be.codesquad.issuetracker.user.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authId;
    private String username;
    private String imageUrl;

    public User(String authId, String username, String imageUrl) {
        this.authId = authId;
        this.username = username;
        this.imageUrl = imageUrl;
    }
}
