package codesquad.issuetracker.dto.member;

import codesquad.issuetracker.domain.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MemberDto {

    private Long id;
    private String identity;
    private String nickname;
    private String profileUrl;

    private MemberDto() {}

    @QueryProjection
    public MemberDto(Long id, String identity, String nickname, String profileUrl) {
        this.id = id;
        this.identity = identity;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
    }

    public static MemberDto convertToDto(Member member) {
        if (member != null) {
            return new MemberDto(member.getId(), member.getIdentity(), member.getName(),
                member.getProfileUrl());
        }

        return null;
    }
}
