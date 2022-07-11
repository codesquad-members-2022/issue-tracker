package louie.hanse.issuetracker.web.controller;

import lombok.RequiredArgsConstructor;
import louie.hanse.issuetracker.domain.Status;
import louie.hanse.issuetracker.service.MileStoneService;
import louie.hanse.issuetracker.web.dto.milestone.MilestoneListResponse;
import louie.hanse.issuetracker.web.dto.milestone.MilestoneSaveRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/milestone")
@RequiredArgsConstructor
public class MileStoneController {

    private final MileStoneService mileStoneService;

    @PostMapping
    public void registerMilestone(@RequestBody MilestoneSaveRequest request) {
        mileStoneService.register(request);
    }

    @GetMapping
    public MilestoneListResponse getMilestoneList(@RequestParam("status") Status status) {
        return mileStoneService.getMilestoneList(status);
    }
}
