package louie.hanse.issuetracker.service;

import lombok.RequiredArgsConstructor;
import louie.hanse.issuetracker.domain.Issue;
import louie.hanse.issuetracker.domain.Milestone;
import louie.hanse.issuetracker.domain.Status;
import louie.hanse.issuetracker.repository.MilestoneRepository;
import louie.hanse.issuetracker.web.dto.milestone.MilestoneListResponse;
import louie.hanse.issuetracker.web.dto.milestone.MilestoneRequest;
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
    public void register(MilestoneRequest request) {
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

    @Transactional
    public void edit(Long id, MilestoneRequest request) {
        Milestone milestone = milestoneRepository.findById(id).orElseThrow(IllegalStateException::new);

        milestone.updateTitle(request.getTitle());
        milestone.updateDescription(request.getDescription());
        milestone.updateCompletedDate(request.getCompletedDate());
    }

    @Transactional
    public void delete(Long id) {
        Milestone milestone = milestoneRepository.findById(id).orElseThrow(IllegalStateException::new);
        List<Issue> issues = milestone.getIssues();
        for (Issue issue : issues) {
            issue.deleteMilestone();
        }
        milestoneRepository.delete(milestone);
    }
}
