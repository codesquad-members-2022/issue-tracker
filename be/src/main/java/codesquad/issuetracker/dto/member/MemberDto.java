package codesquad.issuetracker.dto.member;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MemberDto {

    private Long id;
    private String identity;
    private String name;
    private String profileUrl;

    @QueryProjection
    public MemberDto(Long id, String identity, String name, String profileUrl) {
        this.id = id;
        this.identity = identity;
        this.name = name;
        this.profileUrl = profileUrl;
    }
}
