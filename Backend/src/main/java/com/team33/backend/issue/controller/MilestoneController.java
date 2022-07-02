package com.team33.backend.issue.controller;

import com.team33.backend.issue.controller.dto.milestone.MilestoneEditRequest;
import com.team33.backend.issue.controller.dto.milestone.MilestoneEditResponse;
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
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/issuegroup")
public class MilestoneController {

    private final MilestoneService milestoneService;

    @GetMapping("/{issueGroupId}/milestone/{milestoneId}")
    public MilestoneResponse getMilestoneByIssuegroupId(@PathVariable Long issueGroupId, @PathVariable Long milestoneId) {
        return new MilestoneResponse(milestoneService.findMilestoneByIssuegroupId(milestoneId));
    }

    @GetMapping("/{issueGroupId}/milestones")
    public List<MilestoneResponse> getMilestonesByIssuegroupId(@PathVariable Long issueGroupId) {
        return milestoneService.findMilestoneByIssueId(issueGroupId).stream()
                .map(MilestoneResponse::new).collect(Collectors.toList());
    }

    @PostMapping("/{issueGroupId}/milestones/{milestoneId}")
    public MilestoneCreateResponse createMilestone(@PathVariable Long issueGroupId, @PathVariable Long milestoneId,
                                                   @RequestBody MilestoneCreateRequest request) {
        return new MilestoneCreateResponse(milestoneService.createMilestone(request));
    }

    @PutMapping("/{issueGroupId}/milestones/{milestoneId}")
    public MilestoneEditResponse editMilestone(@PathVariable Long issueGroupId, @PathVariable Long milestoneId,
                                               @RequestBody MilestoneEditRequest request) {
        return new MilestoneEditResponse(milestoneService.editMilestone(milestoneId, request));
    }

    @DeleteMapping("/{issueGroupId}/milestones/{milestoneId}")
    public MilestoneDeleteResponse deleteMilestone(@PathVariable Long issueGroupId, @PathVariable Long milestoneId) {
        return new MilestoneDeleteResponse(milestoneService.deleteMilestoneById(milestoneId));
    }
}
