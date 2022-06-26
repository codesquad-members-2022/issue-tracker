package kr.codesquad.issuetracker.service;

import static kr.codesquad.issuetracker.exception.ErrorMessage.MILESTONE_NOT_FOUND;
import static kr.codesquad.issuetracker.exception.ErrorMessage.MILESTONE_NOT_SAVE;

import java.util.List;
import java.util.stream.Collectors;
import kr.codesquad.issuetracker.domain.Status;
import kr.codesquad.issuetracker.domain.issue.Issue;
import kr.codesquad.issuetracker.domain.label.repository.LabelRepository;
import kr.codesquad.issuetracker.domain.milestone.Milestone;
import kr.codesquad.issuetracker.domain.milestone.repository.MilestoneRepository;
import kr.codesquad.issuetracker.exception.CustomException;
import kr.codesquad.issuetracker.web.dto.milestone.MilestoneDto;
import kr.codesquad.issuetracker.web.dto.milestone.MilestoneEditRequestDto;
import kr.codesquad.issuetracker.web.dto.milestone.MilestoneRequestDto;
import kr.codesquad.issuetracker.web.dto.milestone.MilestoneResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MilestonesService {

	private final MilestoneRepository milestoneRepository;

	private final LabelRepository labelRepository;

	@Transactional(readOnly = true)
	public MilestoneResponseDto list(Status status) {
		return new MilestoneResponseDto(
			(int) labelRepository.count(),
			countOpened(),
			countClosed(),
			findMilestonesDtos(status)
		);
	}

	private int countOpened() {
		return (int) milestoneRepository.countByStatus(Status.OPEN);
	}

	private int countClosed() {
		return (int) milestoneRepository.countByStatus(Status.CLOSED);
	}

	private List<MilestoneDto> findMilestonesDtos(Status status) {
		return milestoneRepository.findAllByStatus(status).stream()
			.map(MilestoneDto::from)
			.collect(Collectors.toList());
	}

	@Transactional
	public void add(MilestoneRequestDto dto) {
		Milestone milestone = milestoneRepository.save(dto.toEntity());
		if (milestone.getId() == null){
			throw new CustomException(MILESTONE_NOT_SAVE);
		}
	}

	@Transactional
	public void delete(Long id) {
		Milestone milestone = milestoneRepository.findById(id).orElseThrow(
			() -> new CustomException(MILESTONE_NOT_FOUND)
		);
		milestoneRepository.delete(milestone);
	}

	@Transactional
	public void update(Long id, MilestoneEditRequestDto dto) {
		Milestone milestone = milestoneRepository.findById(id).orElseThrow(
			() -> new CustomException(MILESTONE_NOT_FOUND)
		);
		milestone.update(dto);
	}

}
