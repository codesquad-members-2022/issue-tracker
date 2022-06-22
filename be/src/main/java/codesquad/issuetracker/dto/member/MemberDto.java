package codesquad.issuetracker.dto.member;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MemberDto {

    private Long id;
    private String identity;
    private String nickname;
    private String profileUrl;

    @QueryProjection
    public MemberDto(Long id, String identity, String nickname, String profileUrl) {
        this.id = id;
        this.identity = identity;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
    }
}
