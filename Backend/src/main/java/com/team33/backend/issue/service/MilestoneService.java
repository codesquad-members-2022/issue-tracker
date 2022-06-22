package com.team33.backend.issue.service;

import com.team33.backend.issue.controller.dto.milestone.MilestomeEditRequest;
import com.team33.backend.issue.controller.dto.milestone.MilestoneCount;
import com.team33.backend.issue.controller.dto.milestone.MilestoneCreateRequest;
import com.team33.backend.issue.controller.dto.milestone.MilestoneResponse;
import com.team33.backend.issue.domain.Milestone;
import com.team33.backend.issue.repository.query.MilestoneQueryRepository;
import com.team33.backend.issue.repository.MilestoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final MilestoneQueryRepository milestoneQueryRepository;

    @Transactional
    public Milestone createMilestone(MilestoneCreateRequest request) {
        Milestone newMilestone = new Milestone(request.getTitle(), request.getDescription(), request.getDeadline());
        return milestoneRepository.save(newMilestone);
    }

    @Transactional
    public Milestone editMilestone(Long milestoneId, MilestomeEditRequest request) {
        Milestone findMilestone = milestoneRepository.findById(milestoneId).orElseThrow();
        findMilestone.editMilestone(request.getTitle(), request.getDescription(), request.getLocalDate());
        return findMilestone;
    }

    @Transactional(readOnly = true)
    public MilestoneCount getIssueMilestoneCount(Long milestoneId) {
        return milestoneQueryRepository.countIssueMilestoneCount(milestoneId);
    }

    @Transactional
    public Milestone deleteMilestoneById(Long milestoneId) {
        Milestone findMilestone = milestoneRepository.findById(milestoneId).orElseThrow();
        milestoneRepository.delete(findMilestone);
        return findMilestone;
    }

    @Transactional(readOnly = true)
    public Milestone findMilestoneById(Long milestoneId) {
        return milestoneRepository.findById(milestoneId).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<MilestoneResponse> findMilestoneByIssueId(Long issueId) {
        return milestoneQueryRepository.findMilestoneByIssueId(issueId);
    }
}
