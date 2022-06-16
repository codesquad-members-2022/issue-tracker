package codesquad.issuetracker.dto.member;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MemberDto {

    private Long memberId;
    private String identity;
    private String nickname;
    private String profileUrl;

    @QueryProjection
    public MemberDto(Long memberId, String identity, String nickname, String profileUrl) {
        this.memberId = memberId;
        this.identity = identity;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
    }
}
