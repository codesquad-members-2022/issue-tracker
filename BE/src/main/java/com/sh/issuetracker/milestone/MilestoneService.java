package com.sh.issuetracker.milestone;

import com.sh.issuetracker.milestone.dto.MilestoneRequest;
import com.sh.issuetracker.milestone.dto.MilestoneResponse;
import com.sh.issuetracker.project.Project;
import com.sh.issuetracker.project.ProjectService;
import com.sh.issuetracker.user.AuthUser;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MilestoneService {
	private final ProjectService projectService;
	private final MilestoneRepository milestoneRepository;

	@Transactional
	public MilestoneResponse create(MilestoneRequest.Creation creationRequest, AuthUser authUser) {
		Project projectInfo = projectService.get(authUser.getProjectId());
		Milestone milestone = milestoneRepository.save(creationRequest.toMilestone(projectInfo));
		return MilestoneResponse.from(milestone);
	}

	@Transactional(readOnly = true)
	public List<MilestoneResponse> readAll(AuthUser authUser) {
		List<Milestone> milestones = milestoneRepository.findAllByProjectId(authUser.getProjectId());
		return milestones.stream()
			.map(MilestoneResponse::from)
			.collect(Collectors.toList());
	}
}
