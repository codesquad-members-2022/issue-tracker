package com.ron2ader.issuetracker.controller.authdto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class Tokens {

    private String accessToken;
    private String refreshToken;
}
