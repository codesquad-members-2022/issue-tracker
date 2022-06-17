package be.codesquad.issuetracker.oauth;

import be.codesquad.issuetracker.user.User;
import be.codesquad.issuetracker.user.UserRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    @Transactional
    public User upsertUser(User user) {
        User findUser = userRepository.findByAuthId(user.getAuthId())
            .orElseThrow(() -> new NoSuchElementException("유효하지 않은 사용자입니다."));

        findUser.update(user);
        userRepository.save(findUser);
        return user;
    }
}
