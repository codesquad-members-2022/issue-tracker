package codesquad.issuetracker.controller;

import codesquad.issuetracker.dto.milestone.MilestoneDtos;
import codesquad.issuetracker.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MilestoneController {

    private final MilestoneService milestoneService;

    @GetMapping("/api/milestones")
    public MilestoneDtos milestones() {
        return milestoneService.getMilestones();
    }
}
