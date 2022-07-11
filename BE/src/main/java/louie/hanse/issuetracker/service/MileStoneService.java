package louie.hanse.issuetracker.service;

import lombok.RequiredArgsConstructor;
import louie.hanse.issuetracker.domain.Milestone;
import louie.hanse.issuetracker.domain.Status;
import louie.hanse.issuetracker.repository.MilestoneRepository;
import louie.hanse.issuetracker.web.dto.milestone.MilestoneListResponse;
import louie.hanse.issuetracker.web.dto.milestone.MilestoneSaveRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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

    public MilestoneListResponse getMilestoneList(Status status) {
        List<Milestone> closedMilestone = milestoneRepository.findClosedMilestone(LocalDate.now());
        List<Milestone> openedMilestone = milestoneRepository.findOpenedMilestone(LocalDate.now());
        if (status.isOpened()) {
            return new MilestoneListResponse(openedMilestone.size(), closedMilestone.size(), openedMilestone);
        }
        return new MilestoneListResponse(openedMilestone.size(), closedMilestone.size(), closedMilestone);
    }
}
