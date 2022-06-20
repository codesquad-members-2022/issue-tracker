package codesquad.issuetracker.service;

import codesquad.issuetracker.dto.milestone.MilestoneDtos;
import codesquad.issuetracker.repository.MilestoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;

    public MilestoneDtos getMilestones() {
        return new MilestoneDtos(milestoneRepository.findAll());
    }
}
