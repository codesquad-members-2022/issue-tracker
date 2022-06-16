package codesquad.issuetracker.dto.member;

import java.util.List;
import lombok.Getter;

@Getter
public class MemberDtoList {

    private List<MemberDto> members;

    public MemberDtoList(List<MemberDto> members) {
        this.members = members;
    }
}
