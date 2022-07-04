package kr.codesquad.issuetracker.service;

import kr.codesquad.issuetracker.domain.Milestone;
import kr.codesquad.issuetracker.dto.MilestoneRequest;
import kr.codesquad.issuetracker.dto.MilestoneResponse;
import kr.codesquad.issuetracker.repository.MilestoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;

    public List<MilestoneResponse> getMilestoneList() {
        return milestoneRepository.findAll().stream()
                .map(MilestoneResponse::new).collect(Collectors.toList());
    }

    public ResponseEntity createMilestone(MilestoneRequest milestoneRequest){
        Milestone milestone = new Milestone(milestoneRequest);
        try {
            milestoneRepository.save(milestone);
        } catch (RuntimeException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity deleteMilestone(Long id){
        try {
            milestoneRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity editMilestone(Long id, MilestoneRequest milestoneRequest){
        Milestone milestone = milestoneRepository.findById(id).orElseThrow(RuntimeException::new);
        milestone.update(milestoneRequest);
        return new ResponseEntity(HttpStatus.OK);
    }
}
