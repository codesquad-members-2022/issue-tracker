package louie.hanse.issuetracker.service;

import lombok.RequiredArgsConstructor;
import louie.hanse.issuetracker.domain.Milestone;
import louie.hanse.issuetracker.repository.MilestoneRepository;
import louie.hanse.issuetracker.web.dto.milestone.MilestoneSaveRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MileStoneService {

    private final MilestoneRepository milestoneRepository;

    @Transactional
    public void register(MilestoneSaveRequest request) {
        Milestone milestone = request.toEntity();
        milestoneRepository.save(milestone);
    }
}
