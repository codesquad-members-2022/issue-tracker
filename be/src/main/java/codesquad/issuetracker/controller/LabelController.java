package codesquad.issuetracker.controller;

import codesquad.issuetracker.dto.label.LabelDtoList;
import codesquad.issuetracker.service.LabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LabelController {

    private final LabelService labelService;

    @GetMapping("/api/labels")
    public LabelDtoList labels() {
        return labelService.getLabels();
    }

}
