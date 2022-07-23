package louie.hanse.issuetracker.web.controller;

import lombok.RequiredArgsConstructor;
import louie.hanse.issuetracker.domain.Status;
import louie.hanse.issuetracker.service.MileStoneService;
import louie.hanse.issuetracker.web.dto.milestone.MilestoneListResponse;
import louie.hanse.issuetracker.web.dto.milestone.MilestoneRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/milestone")
@RequiredArgsConstructor
public class MileStoneController {

    private final MileStoneService mileStoneService;

    @PostMapping
    public void registerMilestone(@RequestBody MilestoneRequest request) {
        mileStoneService.register(request);
    }

    @GetMapping
    public MilestoneListResponse getMilestoneList(@RequestParam("status") Status status) {
        return mileStoneService.getMilestoneList(status);
    }

    @PutMapping("/{id}")
    public void editMilestone(@PathVariable Long id, @RequestBody MilestoneRequest request) {
        mileStoneService.edit(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteMilestone(@PathVariable Long id) {
        mileStoneService.delete(id);
    }
}
