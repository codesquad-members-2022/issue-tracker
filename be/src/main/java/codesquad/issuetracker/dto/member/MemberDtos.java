package codesquad.issuetracker.dto.member;

import java.util.List;
import lombok.Getter;

@Getter
public class MemberDtos {

    private List<MemberDto> members;

    public MemberDtos(List<MemberDto> members) {
        this.members = members;
    }
}
