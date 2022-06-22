package com.sh.issuetracker.label;

import com.sh.issuetracker.label.dto.LabelRequest;
import com.sh.issuetracker.label.dto.LabelResponse;
import com.sh.issuetracker.project.Project;
import com.sh.issuetracker.project.ProjectService;
import com.sh.issuetracker.user.AuthUser;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LabelService {
	private final ProjectService projectService;
	private final LabelRepository labelRepository;

	public LabelResponse create(LabelRequest.Creation request, AuthUser authUser) {
		Project project = projectService.get(authUser.getProjectId());
		Label labelInfo = labelRepository.save(request.toLabel(project));
		return LabelResponse.from(labelInfo);
	}

	public List<LabelResponse> readAll(AuthUser authUser) {
		return labelRepository.findAllByProjectId(authUser.getProjectId()).stream()
			.map(LabelResponse::from)
			.collect(Collectors.toList());
	}
}
