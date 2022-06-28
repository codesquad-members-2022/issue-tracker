package com.ron2ader.issuetracker.controller;

import com.ron2ader.issuetracker.controller.labeldto.LabelRequest;
import com.ron2ader.issuetracker.controller.labeldto.LabelResponse;
import com.ron2ader.issuetracker.controller.labeldto.LabelsResponse;
import com.ron2ader.issuetracker.service.LabelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LabelController {

    private final LabelService labelService;

    @PostMapping("/labels")
    public LabelResponse register(@RequestBody LabelRequest labelRequest) {
        return labelService.save(labelRequest.getTitle(), labelRequest.getColor(), labelRequest.getDescription());
    }

    @GetMapping("/labels")
    public List<LabelsResponse> getLabels() {
        return labelService.findAll();
    }

    @PostMapping("/labels/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LabelResponse update(@PathVariable Long id, @RequestBody LabelRequest labelRequest) {
        LabelResponse updateLabel = labelService.update(
            id,
            labelRequest.getTitle(),
            labelRequest.getColor(),
            labelRequest.getDescription()
        );

        return updateLabel;
    }

    @DeleteMapping("/labels/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        labelService.deleteById(id);
    }
}
