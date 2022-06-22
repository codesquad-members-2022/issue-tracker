package be.codesquad.issuetracker.oauth.service;

import be.codesquad.issuetracker.oauth.dto.GithubUser;
import be.codesquad.issuetracker.user.domain.User;
import be.codesquad.issuetracker.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final UserRepository userRepository;

    @Transactional
    public User upsertUser(GithubUser user) {
//        User findUser = userRepository.findByAuthId(user.getGithubId())
//            .orElseThrow(() -> new NoSuchElementException("유효하지 않은 사용자입니다."));


//        findUser.update(findUser);
        User user1 = new User(user.getGithubId(), user.getUsername(), user.getImageUrl());
        userRepository.save(user1);
        return user1;
    }
}
