package com.team33.backend.member.service;

import com.team33.backend.issue.domain.Assignee;
import com.team33.backend.member.controller.dto.MemberQueryResponse;
import com.team33.backend.member.domain.Member;
import com.team33.backend.member.repository.query.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberQueryRepository memberQueryRepository;

    @Transactional(readOnly = true)
    public List<Member> findGroupMembers(Long groupId) {
        return memberQueryRepository.findGroupMembersById(groupId);
    }
}
