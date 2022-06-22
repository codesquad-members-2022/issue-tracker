package team24.issuetracker.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team24.issuetracker.web.dto.labellist.LabelListResponse;
import team24.issuetracker.web.service.LabelService;

@RestController
@RequestMapping("/labels")
@RequiredArgsConstructor
public class LabelController {

	private final LabelService labelService;

	@GetMapping
	public List<LabelListResponse> findLabels(){
		return labelService.findLabel();
	}
}
