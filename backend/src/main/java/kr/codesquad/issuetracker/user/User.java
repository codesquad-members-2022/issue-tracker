package kr.codesquad.issuetracker.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NonNull;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String userId;
    private String email;
    private String name;

    public User(@NonNull String userId, String email, String name) {
        this.userId = userId;
        this.email = email;
        this.name = name;
    }

    public User() {
    }
}
