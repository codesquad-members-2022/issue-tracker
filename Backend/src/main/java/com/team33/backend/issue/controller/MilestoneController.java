package com.team33.backend.issue.controller;

import com.team33.backend.issue.controller.dto.milestone.MilestomeEditRequest;
import com.team33.backend.issue.controller.dto.milestone.MilestomeEditResponse;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/issuegroup")
public class MilestoneController {

    private final MilestoneService milestoneService;

    @GetMapping("/{issuegroupId}/milestone/{milestoneId}")
    public MilestoneResponse getMilestoneByIssuegroupId(@PathVariable Long issuegroupId, @PathVariable Long milestoneId) {
        return new MilestoneResponse(milestoneService.findMilestoneByIssuegroupId(milestoneId));
    }

    @GetMapping("/{issuegroupId}/milestones")
    public List<MilestoneResponse> getMilestonesByIssuegroupId(@PathVariable Long issuegroupId) {
        return milestoneService.findMilestoneByIssueId(issuegroupId);
    }

    @PostMapping("/{issuegroupId}/milestones/{milestoneId}")
    public MilestoneCreateResponse createMilestone(@PathVariable Long issuegroupId, @PathVariable Long milestoneId,
                                                   @RequestBody MilestoneCreateRequest request) {
        return new MilestoneCreateResponse(milestoneService.createMilestone(request));
    }

    @PutMapping("/{issuegroupId}/milestones/{milestoneId}")
    public MilestomeEditResponse editMilestone(@PathVariable Long issuegroupId, @PathVariable Long milestoneId,
                                               @RequestBody MilestomeEditRequest request) {
        return new MilestomeEditResponse(milestoneService.editMilestone(milestoneId, request));
    }

    @DeleteMapping("/{issuegroupId}/milestones/{milestoneId}")
    public MilestoneDeleteResponse deleteMilestone(@PathVariable Long issuegroupId, @PathVariable Long milestoneId) {
        return new MilestoneDeleteResponse(milestoneService.deleteMilestoneById(milestoneId));
    }
}
