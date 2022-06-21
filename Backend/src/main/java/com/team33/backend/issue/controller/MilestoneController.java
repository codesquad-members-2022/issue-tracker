package com.team33.backend.issue.controller;

import com.team33.backend.issue.controller.dto.milestone.MilestomeEditRequest;
import com.team33.backend.issue.controller.dto.milestone.MilestomeEditResponse;
import com.team33.backend.issue.controller.dto.milestone.MilestoneCount;
import com.team33.backend.issue.controller.dto.milestone.MilestoneCreateRequest;
import com.team33.backend.issue.controller.dto.milestone.MilestoneCreateResponse;
import com.team33.backend.issue.controller.dto.milestone.MilestoneDeleteResponse;
import com.team33.backend.issue.controller.dto.milestone.MilestoneResponse;
import com.team33.backend.issue.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/milestones")
public class MilestoneController {

    private final MilestoneService milestoneService;

    @GetMapping("/{milestoneId}")
    public MilestoneResponse getMilestones(@PathVariable Long milestoneId) {
        return new MilestoneResponse(milestoneService.findMilestoneById(milestoneId));
    }

    @PostMapping
    public MilestoneCreateResponse createMilestone(@RequestBody MilestoneCreateRequest request) {
        return new MilestoneCreateResponse(milestoneService.createMilestone(request));
    }

    @PutMapping("/{milestoneId}")
    public MilestomeEditResponse editMilestone(@PathVariable Long milestoneId, @RequestBody MilestomeEditRequest request) {
        return new MilestomeEditResponse(milestoneService.editMilestone(milestoneId, request));
    }

    @GetMapping("/counts/{milestoneId}")
    public MilestoneCount countIssueMilestone(@PathVariable Long milestoneId) {
        return milestoneService.getIssueMilestoneCount(milestoneId);
    }

    @DeleteMapping("/{milestoneId}")
    public MilestoneDeleteResponse deleteMilestone(@PathVariable Long milestoneId) {
        return new MilestoneDeleteResponse(milestoneService.deleteMilestoneById(milestoneId));
    }
}
