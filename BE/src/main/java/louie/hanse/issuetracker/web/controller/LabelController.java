package louie.hanse.issuetracker.web.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import louie.hanse.issuetracker.service.LabelService;
import louie.hanse.issuetracker.web.dto.label.LabelEditRequest;
import louie.hanse.issuetracker.web.dto.label.LabelListResponse;
import louie.hanse.issuetracker.web.dto.label.LabelSaveRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/labels")
@RestController
public class LabelController {

    private final LabelService labelService;

    @GetMapping
    public LabelListResponse getLabelList() {
        return labelService.getLabelList();
    }

    @PostMapping
    public void registerLabel(@Valid @RequestBody LabelSaveRequest request) {
        labelService.register(request);
    }

    @PutMapping("/{id}")
    public void editLabel(@PathVariable Long id, @Valid @RequestBody LabelEditRequest request) {
        labelService.edit(id, request);
    }
}
