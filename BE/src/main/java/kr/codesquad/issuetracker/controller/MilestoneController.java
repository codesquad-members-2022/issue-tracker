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
public class MilestoneController {

    private final MilestoneService milestoneService;

    @GetMapping("/milestones")
    public List<MilestoneResponse> getMilestones(){
        return milestoneService.getMilestoneList();
    }

    @PostMapping("/milestones")
    public ResponseEntity createMilestone (@RequestBody MilestoneRequest milestoneRequest){
        try {
            milestoneService.createMilestone(milestoneRequest);
        } catch (RuntimeException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/milestones/{id}")
    public ResponseEntity deleteMilestone (@PathVariable Long id){
        try {
            milestoneService.deleteMilestone(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
