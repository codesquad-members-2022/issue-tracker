package dev.kukim.issues.milestone.controller;

import dev.kukim.issues.milestone.controller.request.MilestoneCreateRequest;
import dev.kukim.issues.milestone.controller.response.MileStoneResponse;
import dev.kukim.issues.milestone.controller.response.MilestoneListResponse;
import dev.kukim.issues.milestone.service.MilestoneService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/milestones")
public class MilestoneController {

	private final MilestoneService milestoneService;

	@GetMapping
	public MilestoneListResponse read(@RequestParam String status) {
		return milestoneService.findAllBy(status);
	}

	@PostMapping
	public MileStoneResponse save(@Valid @RequestBody MilestoneCreateRequest milestoneCreateRequest) {
		return milestoneService.save(milestoneCreateRequest);
	}
}
