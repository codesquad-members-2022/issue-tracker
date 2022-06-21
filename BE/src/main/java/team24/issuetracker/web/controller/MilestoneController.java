package team24.issuetracker.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import team24.issuetracker.web.dto.milestonelist.MilestoneListResponse;
import team24.issuetracker.web.service.MilestoneService;

@RestController
@RequestMapping("/milestones")
@RequiredArgsConstructor
public class MilestoneController {

	private final MilestoneService milestoneService;

	@GetMapping
	public List<MilestoneListResponse> findMilestones() {
		return milestoneService.findAll();
	}
}
