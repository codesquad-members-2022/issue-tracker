package com.team09.issue_tracker.issue.dto;

import com.team09.issue_tracker.exception.MemberInvalidException;
import com.team09.issue_tracker.member.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IssueAssigneeService {

	private final MemberService memberService;

	public void validateAssigneeIds(List<Long> assigneeIds) {
		if (assigneeIds.size() > 0) {
			validateMember(assigneeIds);
		}
	}

	public void validateMember(List<Long> assigneeIds) {
		if (!memberService.validateMemberIds(assigneeIds)) {
			throw new MemberInvalidException();
		}
	}
}
