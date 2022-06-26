package be.codesquad.issuetracker.oauth.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthLoginDto {

    private final String jwtToken;
}
