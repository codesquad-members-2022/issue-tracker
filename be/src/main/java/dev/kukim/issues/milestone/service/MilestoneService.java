package dev.kukim.issues.milestone.service;

import dev.kukim.issues.common.domain.Status;
import dev.kukim.issues.milestone.controller.request.MilestoneCreateRequest;
import dev.kukim.issues.milestone.controller.request.MilestoneUpdateRequest;
import dev.kukim.issues.milestone.controller.response.MilestoneResponse;
import dev.kukim.issues.milestone.controller.response.MilestoneListResponse;
import dev.kukim.issues.milestone.domain.Milestone;
import dev.kukim.issues.milestone.domain.repository.MileStoneRepository;
import dev.kukim.issues.milestone.exception.MilestoneNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MilestoneService {

	private final MileStoneRepository mileStoneRepository;

	public MilestoneListResponse findAllBy(String status) {
		boolean statusBoolean = Status.statusToBoolean(status);

		List<MilestoneResponse> milestoneRespons = mileStoneRepository.findAllByIsOpen(
				statusBoolean)
			.stream()
			.map(MilestoneResponse::createBy)
			.collect(Collectors.toList());

		return MilestoneListResponse.of(milestoneRespons);
	}

	public MilestoneResponse save(MilestoneCreateRequest milestoneCreateRequest) {
		Milestone saveMilestone = mileStoneRepository.save(Milestone.createBy(milestoneCreateRequest));

		return MilestoneResponse.createBy(saveMilestone);
	}

	public MilestoneResponse update(Long milestoneId,
		MilestoneUpdateRequest milestoneUpdateRequest) {
		Milestone findMilestone = mileStoneRepository.findById(milestoneId)
			.orElseThrow(MilestoneNotFoundException::new);

		findMilestone.update(milestoneUpdateRequest);
		Milestone updatedMilestone = mileStoneRepository.save(findMilestone);

		return MilestoneResponse.createBy(updatedMilestone);
	}
}
