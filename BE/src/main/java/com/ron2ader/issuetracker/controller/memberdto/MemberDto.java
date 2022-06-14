package com.ron2ader.issuetracker.controller.memberdto;

import com.ron2ader.issuetracker.domain.member.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberDto {

    private String userId;
    private String avatarUrl;

    public static MemberDto from(Member member) {
        return new MemberDto(member.getUserId(), member.getAvatarUrl());
    }
}
