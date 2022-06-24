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
	public MilestoneResponseDto list(String statusValue) {
		Status status = isStatus(statusValue);
		return new MilestoneResponseDto(
			(int) labelRepository.count(),
			isOpenCount(),
			isClosedCount(),
			findMilestonesDtos(status)
		);
	}

	private Status isStatus(String status) {
		String value = status.toUpperCase();
		if (Status.OPEN.getValue().equals(value)) {
			return Status.OPEN;
		}
		return Status.CLOSED;
	}

	private int isOpenCount() {
		return (int) milestoneRepository.countByStatus(Status.OPEN);
	}

	private int isClosedCount() {
		return (int) milestoneRepository.countByStatus(Status.CLOSED);
	}

	private List<MilestoneDto> findMilestonesDtos(Status status) {
		return milestoneRepository.findAllByStatus(status).stream()
			.map(MilestoneDto::of)
			.collect(Collectors.toList());
	}

	@Transactional
	public void add(MilestoneRequestDto dto) {
		Milestone milestone = milestoneRepository.save(dto.toEntity());
		if (milestone.getId() == null){
			throw new CustomException(MILESTONE_NOT_SAVE);
		}
	}

	//TODO: FK삭제를 어떻게 해야하나.....
	@Transactional
	public void delete(Long id) {
		Milestone milestone = milestoneRepository.findById(id).orElseThrow(
			() -> new CustomException(MILESTONE_NOT_FOUND)
		);
		milestone.getIssues().stream()
			.map(Issue::deleteMilestone);
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
