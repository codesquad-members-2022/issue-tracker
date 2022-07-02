package com.team33.backend.member.controller.dto;

import com.team33.backend.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberQueryResponse {

    private final Long id;
    private final String name;
    private final String profileImageUrl;

    public MemberQueryResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.profileImageUrl = member.getProfileImageUrl();
    }
}
