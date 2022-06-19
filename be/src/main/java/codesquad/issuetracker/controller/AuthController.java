package codesquad.issuetracker.controller;

import codesquad.issuetracker.dto.auth.LoginResponse;
import codesquad.issuetracker.service.OAuthService;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final OAuthService oAuthService;

    @GetMapping("/api/login")
    public ResponseEntity<LoginResponse> login(HttpServletResponse response, @RequestParam String code) {
        Long memberId = oAuthService.authorizeForThirdParty(code);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
