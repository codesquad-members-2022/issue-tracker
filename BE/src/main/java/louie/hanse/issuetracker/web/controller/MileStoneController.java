package louie.hanse.issuetracker.web.controller;

import lombok.RequiredArgsConstructor;
import louie.hanse.issuetracker.service.MileStoneService;
import louie.hanse.issuetracker.web.dto.milestone.MilestoneSaveRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/milestone")
@RequiredArgsConstructor
public class MileStoneController {

    private final MileStoneService mileStoneService;

    @PostMapping
    public void registerMilestone(@RequestBody MilestoneSaveRequest request) {
        mileStoneService.register(request);
    }
}
