package com.team09.issue_tracker.milestone;

import com.team09.issue_tracker.exception.MilestoneInvalidException;
import com.team09.issue_tracker.member.Member;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MilestoneService {

	private final MilestoneRepository milestoneRepository;

	public Milestone createMilestone(Long milestoneId, Long memberId) {
		return Optional.ofNullable(milestoneId)
			.map(Milestone::of)
			.orElse(null);
	}

	public void validateMilestoneId(Long milestoneId, Long memberId) {
		Optional.ofNullable(milestoneId)
			.ifPresent(
				extractedMilestoneId -> validateMyMilestoneId(extractedMilestoneId, memberId));
	}

	private void validateMyMilestoneId(Long milestoneId, Long memberId) {
		if (!isMyMilestone(milestoneId, memberId)) {
			throw new MilestoneInvalidException();
		}
	}

	@Transactional(readOnly = true)
	boolean isMyMilestone(Long milestoneId, Long memberId) {
		Member member = Member.of(memberId);
		long countOfMyMilestone = milestoneRepository.countByIdAndWriter(milestoneId, member);

		return countOfMyMilestone == 1;
	}
}
