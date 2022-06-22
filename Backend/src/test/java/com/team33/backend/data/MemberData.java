package com.team33.backend.data;

import com.team33.backend.member.domain.Member;

public class MemberData {

    private Member member;

    public Member getMember() {
        if (member == null) {
            this.member = createMember();
        }
        return member;
    }

    private Member createMember() {
        return new Member();
    }
}
