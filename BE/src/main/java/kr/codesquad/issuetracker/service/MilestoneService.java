package kr.codesquad.issuetracker.service;

import kr.codesquad.issuetracker.domain.Milestone;
import kr.codesquad.issuetracker.dto.MilestoneRequest;
import kr.codesquad.issuetracker.dto.MilestoneResponse;
import kr.codesquad.issuetracker.repository.MilestoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;

    //TODO: 열린이슈와 닫힌이슈 리스트 생성해서 리턴하기
    public List<MilestoneResponse> getMilestoneList() {
        return milestoneRepository.findAll().stream()
                .map(MilestoneResponse::new).collect(Collectors.toList());
    }

    public void createMilestone(MilestoneRequest milestoneRequest){
        Milestone milestone = new Milestone(milestoneRequest);
        milestoneRepository.save(milestone);
    }

    public void deleteMilestone(Long id){
        milestoneRepository.deleteById(id);
    }
}
