package codesquad.issuetracker.controller;

import codesquad.issuetracker.dto.milestone.MileStoneDtoList;
import codesquad.issuetracker.service.MileStoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MileStoneController {

    private final MileStoneService mileStoneService;

    @GetMapping("/api/milestones")
    public MileStoneDtoList mileStones() {
        return mileStoneService.getMileStones();
    }
}
