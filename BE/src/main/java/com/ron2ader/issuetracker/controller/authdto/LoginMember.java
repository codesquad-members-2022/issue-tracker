package com.ron2ader.issuetracker.controller.authdto;

import com.ron2ader.issuetracker.domain.member.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LoginMember {

    private Long id;
    private String memberId;
    private String avatarUrl;

    public static LoginMember from(Member member) {
        return new LoginMember(member.getId(), member.getMemberId(), member.getAvatarUrl());
    }
}
