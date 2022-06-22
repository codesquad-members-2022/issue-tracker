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
    public User upsertUser(GithubUser githubUser) {
        User user = new User(githubUser.getId(), githubUser.getUsername(),
            githubUser.getImageUrl());
        return userRepository.findByAuthId(githubUser.getId()).orElseGet(() ->
            userRepository.save(user));
    }
}
