package com.ron2ader.issuetracker.controller;

import com.ron2ader.issuetracker.controller.milestonedto.MilestoneRequest;
import com.ron2ader.issuetracker.controller.milestonedto.MilestoneResponse;
import com.ron2ader.issuetracker.service.MilestoneService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MilestoneController {

    private final MilestoneService milestoneService;

    @GetMapping("/milestones")
    public List<MilestoneResponse> getMilestones() {
        return milestoneService.findAll();
    }

    @PostMapping("/milestones")
    public MilestoneResponse register(@RequestBody MilestoneRequest milestoneRequest) {
        return milestoneService.save(milestoneRequest.getTitle(),
                milestoneRequest.getDescription(),
                milestoneRequest.getEndDate());
    }

    @PostMapping("/milestones/{id}")
    public MilestoneResponse update(@PathVariable Long id, @RequestBody MilestoneRequest milestoneRequest) {
        return milestoneService.update(id, milestoneRequest.getTitle(),
                milestoneRequest.getDescription(),
                milestoneRequest.getEndDate());
    }

    @DeleteMapping("/milestones/{id}")
    public void delete(@PathVariable Long id) {
        milestoneService.delete(id);
    }

}
