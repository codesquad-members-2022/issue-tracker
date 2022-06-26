package kr.codesquad.issuetracker.web.controller;


import kr.codesquad.issuetracker.domain.Status;
import kr.codesquad.issuetracker.service.MilestonesService;
import kr.codesquad.issuetracker.web.dto.milestone.MilestoneEditRequestDto;
import kr.codesquad.issuetracker.web.dto.milestone.MilestoneRequestDto;
import kr.codesquad.issuetracker.web.dto.milestone.MilestoneResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/milestones")
@RestController
public class MilestonesController {

	private final MilestonesService milestonesService;

	@GetMapping
	public MilestoneResponseDto milestoneList(@RequestParam Status status) {
		log.debug("마일스톤 조회");
		return milestonesService.list(status);
	}

	@PostMapping
	public void milestoneAdd(@RequestBody MilestoneRequestDto dto) {
		log.debug("마일스톤 추가");
		milestonesService.add(dto);
	}

	@DeleteMapping("/{id}")
	public void milestoneDelete(@PathVariable Long id) {
		log.debug("마일스톤 삭제");
		milestonesService.delete(id);
	}

	@PostMapping("{id}")
	public void milestoneEdit(@PathVariable Long id, @RequestBody MilestoneEditRequestDto dto) {
		log.debug("마일스톤 수정");
		milestonesService.update(id, dto);
	}
}
