package com.ron2ader.issuetracker.controller.memberdto;

import com.ron2ader.issuetracker.domain.member.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberDto {

    private Long id;
    private String memberId;
    private String avatarUrl;

    public static MemberDto from(Member member) {
        return new MemberDto(member.getId(), member.getMemberId(), member.getAvatarUrl());
    }
}
