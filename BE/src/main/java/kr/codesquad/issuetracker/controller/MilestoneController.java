package kr.codesquad.issuetracker.controller;

import kr.codesquad.issuetracker.dto.MilestoneRequest;
import kr.codesquad.issuetracker.dto.MilestoneResponse;
import kr.codesquad.issuetracker.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/milestones")
public class MilestoneController {

    private final MilestoneService milestoneService;

    @GetMapping
    public List<MilestoneResponse> getMilestones(){
        return milestoneService.getMilestoneList();
    }

    @PostMapping
    public ResponseEntity createMilestone (@RequestBody MilestoneRequest milestoneRequest){
        return milestoneService.createMilestone(milestoneRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMilestone (@PathVariable Long id){
        return milestoneService.deleteMilestone(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity editMilestone (@PathVariable Long id, @RequestBody MilestoneRequest milestoneRequest){
        return milestoneService.editMilestone(id, milestoneRequest);
    }
}
