package com.team09.issue_tracker.issue;

import com.team09.issue_tracker.exception.LabelInvalidException;
import com.team09.issue_tracker.exception.MemberInvalidException;
import com.team09.issue_tracker.exception.MilestoneInvalidException;
import com.team09.issue_tracker.label.LabelService;
import com.team09.issue_tracker.member.MemberService;
import com.team09.issue_tracker.milestone.MilestoneService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IssueValidateService {

	private final MilestoneService milestoneService;
	private final LabelService labelService;
	private final MemberService memberService;

	public void validateMyMilestoneId(Long milestoneId, Long memberId) {
		if (!milestoneService.isMyMilestone(milestoneId, memberId)) {
			throw new MilestoneInvalidException();
		}
	}

	public void validateMyLabelIds(List<Long> labelIds, Long memberId) {
		if (!labelService.isMyLabels(labelIds, memberId)) {
			throw new LabelInvalidException();
		}
	}

	public void validateMember(List<Long> assigneeIds) {
		if (!memberService.validateMemberIds(assigneeIds)) {
			throw new MemberInvalidException();
		}
	}

}
