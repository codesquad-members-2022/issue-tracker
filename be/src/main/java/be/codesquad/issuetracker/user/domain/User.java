package be.codesquad.issuetracker.user.domain;

import be.codesquad.issuetracker.oauth.dto.GithubUser;
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

    public void update(User user) {
        this.authId = user.getAuthId();
        this.username = user.getUsername();
        this.imageUrl = user.getImageUrl();
    }

    public static User of(GithubUser githubUser) {
        return new User(githubUser.getId(), githubUser.getUsername(), githubUser.getImageUrl());
    }
}
