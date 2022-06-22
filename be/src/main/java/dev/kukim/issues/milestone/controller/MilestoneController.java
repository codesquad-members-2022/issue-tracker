package dev.kukim.issues.milestone.controller;

import dev.kukim.issues.milestone.controller.request.MilestoneCreateRequest;
import dev.kukim.issues.milestone.controller.request.MilestoneUpdateRequest;
import dev.kukim.issues.milestone.controller.response.MilestoneResponse;
import dev.kukim.issues.milestone.controller.response.MilestoneListResponse;
import dev.kukim.issues.milestone.service.MilestoneService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public MilestoneResponse save(@Valid @RequestBody MilestoneCreateRequest milestoneCreateRequest) {
		return milestoneService.save(milestoneCreateRequest);
	}

	@PatchMapping("/{milestoneId}")
	public MilestoneResponse update(@PathVariable Long milestoneId,
		@RequestBody MilestoneUpdateRequest milestoneUpdateRequest) {
		return milestoneService.update(milestoneId, milestoneUpdateRequest);
	}
}
