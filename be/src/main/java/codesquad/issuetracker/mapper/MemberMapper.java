package codesquad.issuetracker.mapper;

import codesquad.issuetracker.domain.Member;
import codesquad.issuetracker.dto.member.MemberDto;
import java.util.List;
import java.util.stream.Collectors;

public class MemberMapper {

    public static MemberDto convertToDto(Member member) {
        if (member != null) {
            return new MemberDto(member.getId(), member.getIdentity(), member.getName(),
                member.getProfileUrl());
        }

        return null;
    }

    public static List<MemberDto> convertToListDto(List<Member> members) {
        return members.stream().map(MemberMapper::convertToDto).collect(Collectors.toList());
    }
}
