package com.team09.issue_tracker.milestone;

import com.team09.issue_tracker.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MilestoneService {

	private final MilestoneRepository milestoneRepository;

	@Transactional(readOnly = true)
	public boolean isMyMilestone(Long milestoneId, Long memberId) {
		Member member = Member.of(memberId);
		long countOfMyMilestone = milestoneRepository.countByIdAndWriter(milestoneId, member);

		if (countOfMyMilestone == 1) {
			return true;
		}
		return false;
	}
}
