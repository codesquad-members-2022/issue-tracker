package kr.codesquad.issuetracker.controller;

import kr.codesquad.issuetracker.dto.MilestoneResponse;
import kr.codesquad.issuetracker.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MilestoneController {

    private final MilestoneService milestoneService;

    @GetMapping("/milestones")
    public List<MilestoneResponse> getMilestones(){
        return milestoneService.getMilestoneList();
    }

}
