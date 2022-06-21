package dev.kukim.issues.milestone.service;

import dev.kukim.issues.common.domain.Status;
import dev.kukim.issues.milestone.controller.request.MilestoneCreateRequest;
import dev.kukim.issues.milestone.controller.response.MileStoneResponse;
import dev.kukim.issues.milestone.controller.response.MilestoneListResponse;
import dev.kukim.issues.milestone.domain.Milestone;
import dev.kukim.issues.milestone.domain.repository.MileStoneRepository;
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

		List<MileStoneResponse> mileStoneResponses = mileStoneRepository.findAllByIsOpen(
				statusBoolean)
			.stream()
			.map(MileStoneResponse::createBy)
			.collect(Collectors.toList());

		return MilestoneListResponse.of(mileStoneResponses);
	}

	public MileStoneResponse save(MilestoneCreateRequest milestoneCreateRequest) {
		Milestone saveMilestone = mileStoneRepository.save(Milestone.of(milestoneCreateRequest));

		return MileStoneResponse.createBy(saveMilestone);
	}
}
