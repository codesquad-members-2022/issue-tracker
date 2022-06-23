package kr.codesquad.issuetracker.controller;

import kr.codesquad.issuetracker.dto.LabelResponse;
import kr.codesquad.issuetracker.service.LabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LabelController {

    private final LabelService labelService;

    @GetMapping("/labels")
    public List<LabelResponse> getLabelList() {
        return labelService.getLabelList();
    }
}
